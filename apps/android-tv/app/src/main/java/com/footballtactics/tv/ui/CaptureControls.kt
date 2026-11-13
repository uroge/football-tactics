package com.footballtactics.tv.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import androidx.tv.material3.darkColorScheme
import com.footballtactics.tv.R
import com.footballtactics.tv.session.CapturePhase
import com.footballtactics.tv.session.CaptureSession

@Composable
internal fun CaptureControls(
    session: CaptureSession,
    requestOverlayPermission: () -> Unit,
    requestCapture: () -> Unit,
    startOverlay: () -> Unit,
    toggleMark: () -> Unit,
    stop: () -> Unit,
) {
    val state by session.state.collectAsState()
    val capturing = state.phase == CapturePhase.Capturing
    val running = capturing || state.phase == CapturePhase.OverlayOnly
    val idle = state.phase == CapturePhase.Idle

    MaterialTheme(colorScheme = darkColorScheme()) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(horizontal = 48.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(stringResource(R.string.app_name), style = MaterialTheme.typography.headlineMedium)
                Text(stringResource(when (state.phase) {
                    CapturePhase.Idle -> R.string.idle
                    CapturePhase.RequestingConsent -> R.string.waiting_for_consent
                    CapturePhase.Starting -> R.string.starting
                    CapturePhase.OverlayOnly -> R.string.overlay_active
                    CapturePhase.Capturing -> R.string.capture_active
                    CapturePhase.Stopping -> R.string.stopping
                }))
                Text(stringResource(if (state.markVisible) R.string.mark_on else R.string.mark_off))
                state.observation?.let { Text(it) }
                state.message?.let { Text(it) }
                Button(onClick = if (running) stop else requestCapture, enabled = running || idle) {
                    Text(stringResource(if (running) R.string.stop else R.string.start_capture))
                }
                Button(onClick = if (capturing) toggleMark else startOverlay, enabled = capturing || idle) {
                    Text(stringResource(when {
                        state.markVisible && capturing -> R.string.hide_mark
                        capturing -> R.string.show_mark
                        else -> R.string.overlay_only
                    }))
                }
                Button(onClick = requestOverlayPermission, enabled = idle) {
                    Text(stringResource(R.string.overlay_settings))
                }
            }
        }
    }
}
