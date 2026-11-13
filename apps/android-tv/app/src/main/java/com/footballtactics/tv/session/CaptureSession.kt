package com.footballtactics.tv.session

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal enum class CapturePhase { Idle, RequestingConsent, Starting, OverlayOnly, Capturing, Stopping }

internal data class CaptureState(
    val phase: CapturePhase = CapturePhase.Idle,
    val markVisible: Boolean = false,
    val observation: String? = null,
    val message: String? = null,
)

/** Shared by the activity and service. All transitions run on the main thread. */
internal class CaptureSession {
    private val mutableState = MutableStateFlow(CaptureState())
    val state = mutableState.asStateFlow()

    fun requestConsent() = enter(CapturePhase.Idle, CapturePhase.RequestingConsent)

    fun acceptConsent() = enter(CapturePhase.RequestingConsent, CapturePhase.Starting)

    fun requestOverlay() = enter(CapturePhase.Idle, CapturePhase.Starting)

    fun overlayStarted() = enter(CapturePhase.Starting, CapturePhase.OverlayOnly)

    fun captureStarted() = enter(CapturePhase.Starting, CapturePhase.Capturing)

    fun stopping() {
        mutableState.value = CaptureState(phase = CapturePhase.Stopping)
    }

    fun finish(message: String? = null) {
        mutableState.value = CaptureState(message = message)
    }

    fun setMarkVisible(visible: Boolean) {
        mutableState.value = state.value.copy(markVisible = visible, observation = null)
    }

    fun observe(observation: String) {
        if (state.value.phase == CapturePhase.Capturing) {
            mutableState.value = state.value.copy(observation = observation)
        }
    }

    private fun enter(from: CapturePhase, to: CapturePhase): Boolean {
        if (state.value.phase != from) return false
        mutableState.value = CaptureState(phase = to)
        return true
    }
}
