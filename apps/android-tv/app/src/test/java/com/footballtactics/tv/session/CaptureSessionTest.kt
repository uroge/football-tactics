package com.footballtactics.tv.session

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CaptureSessionTest {
    @Test
    fun consentIsSingleUseAndEachStopRequiresFreshConsent() {
        val session = CaptureSession()

        assertFalse(session.acceptConsent())
        assertTrue(session.requestConsent())
        assertFalse(session.requestConsent())
        assertTrue(session.acceptConsent())
        assertFalse(session.acceptConsent())
        session.captureStarted()
        assertEquals(CapturePhase.Capturing, session.state.value.phase)

        session.stopping()
        session.finish()
        assertFalse(session.acceptConsent())
        assertTrue(session.requestConsent())
    }

    @Test
    fun denialAndFailureReturnToIdleWithAReason() {
        val session = CaptureSession()

        session.requestConsent()
        session.finish("Consent denied")
        assertEquals(CaptureState(message = "Consent denied"), session.state.value)

        session.requestConsent()
        session.acceptConsent()
        session.finish("Capture unavailable")
        assertEquals(CaptureState(message = "Capture unavailable"), session.state.value)
    }

    @Test
    fun stoppingClearsTheOverlayAndPreviousFrameEvidence() {
        val session = CaptureSession()
        session.requestConsent()
        session.acceptConsent()
        session.captureStarted()
        session.setMarkVisible(true)
        session.observe("Moving pixels")

        session.stopping()
        session.finish()

        assertEquals(CaptureState(), session.state.value)
    }
}
