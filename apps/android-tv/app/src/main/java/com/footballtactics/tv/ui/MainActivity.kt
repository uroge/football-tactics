package com.footballtactics.tv.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import com.footballtactics.tv.R
import com.footballtactics.tv.platform.CaptureService
import com.footballtactics.tv.session.CapturePhase

class MainActivity : ComponentActivity() {
    private val session = CaptureService.session
    private var pendingConsent: ActivityResult? = null

    private val requestCapture = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (session.state.value.phase != CapturePhase.RequestingConsent) return@registerForActivityResult
        if (result.resultCode != RESULT_OK || result.data == null) {
            session.finish(getString(R.string.capture_denied))
        } else {
            pendingConsent = result
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) startConsentedCapture()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaptureControls(
                session = session,
                requestOverlayPermission = ::openOverlaySettings,
                requestCapture = ::requestCaptureConsent,
                startOverlay = ::startOverlay,
                toggleMark = { command(CaptureService.TOGGLE_MARK) },
                stop = { command(CaptureService.STOP) },
            )
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        startConsentedCapture()
    }

    private fun requestCaptureConsent() {
        if (!session.requestConsent()) return
        val projection = getSystemService(MediaProjectionManager::class.java)
        try {
            requestCapture.launch(projection.createScreenCaptureIntent())
        } catch (failure: ActivityNotFoundException) {
            session.finish(getString(R.string.capture_unavailable))
        }
    }

    private fun startConsentedCapture() {
        val result = pendingConsent ?: return
        pendingConsent = null
        if (!session.acceptConsent()) return
        val intent = Intent(this, CaptureService::class.java).setAction(CaptureService.START_CAPTURE)
            .putExtra(CaptureService.RESULT_CODE, result.resultCode)
            .putExtra(CaptureService.CONSENT, result.data)
        startForegroundSession(intent)
    }

    private fun startOverlay() {
        if (!checkOverlayPermission() || !session.requestOverlay()) return
        startForegroundSession(Intent(this, CaptureService::class.java).setAction(CaptureService.START_OVERLAY))
    }

    private fun command(action: String) {
        if (action == CaptureService.TOGGLE_MARK && !session.state.value.markVisible && !checkOverlayPermission()) return
        startService(Intent(this, CaptureService::class.java).setAction(action))
    }

    private fun startForegroundSession(intent: Intent) {
        try {
            ContextCompat.startForegroundService(this, intent)
        } catch (failure: RuntimeException) {
            session.finish(getString(R.string.service_unavailable, failure.javaClass.simpleName))
        }
    }

    private fun checkOverlayPermission(): Boolean {
        if (Settings.canDrawOverlays(this)) return true
        openOverlaySettings()
        return false
    }

    private fun openOverlaySettings() {
        try {
            startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName")))
        } catch (failure: ActivityNotFoundException) {
            android.widget.Toast.makeText(this, R.string.overlay_unavailable, android.widget.Toast.LENGTH_LONG).show()
        }
    }
}
