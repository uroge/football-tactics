package com.footballtactics.tv.platform

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.SystemClock
import android.provider.Settings
import android.util.Log
import androidx.core.app.ServiceCompat
import com.footballtactics.tv.R
import com.footballtactics.tv.session.CapturePhase
import com.footballtactics.tv.session.CaptureSession
import com.footballtactics.tv.ui.MainActivity

internal class CaptureService : Service() {
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var overlay: MarkOverlay
    private var capture: ProjectionCapture? = null
    private var lastLogAt: Long? = null

    override fun onCreate() {
        super.onCreate()
        overlay = MarkOverlay(this)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        try {
            when (intent?.action) {
                START_CAPTURE, START_OVERLAY -> if (session.state.value.phase == CapturePhase.Starting) {
                    startSession(intent)
                }
                TOGGLE_MARK -> if (session.state.value.phase == CapturePhase.Capturing) {
                    setMarkVisible(!session.state.value.markVisible)
                }
                STOP -> end("Stopped by user")
                else -> end("Session ended")
            }
        } catch (failure: RuntimeException) {
            end("Session failed (${failure.javaClass.simpleName})")
        }
        return START_NOT_STICKY
    }

    private fun startSession(intent: Intent) {
        val projecting = intent.action == START_CAPTURE
        val type = when {
            projecting -> ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION
            Build.VERSION.SDK_INT >= 34 -> ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
            else -> 0
        }
        ServiceCompat.startForeground(this, NOTIFICATION_ID, notification(projecting), type)
        if (projecting) {
            val consent = checkNotNull(androidx.core.content.IntentCompat.getParcelableExtra(
                intent, CONSENT, Intent::class.java,
            ))
            val projection = ProjectionCapture(this, handler, ::observe, ::end)
            capture = projection
            projection.start(intent.getIntExtra(RESULT_CODE, 0), consent)
            session.captureStarted()
            Log.i(TAG, "Capture started")
        } else {
            session.overlayStarted()
            setMarkVisible(true)
        }
    }

    private fun setMarkVisible(visible: Boolean) {
        if (visible) {
            check(Settings.canDrawOverlays(this)) { "Overlay permission required" }
            overlay.show()
        } else {
            overlay.hide()
        }
        session.setMarkVisible(visible)
        capture?.resetInspection()
        lastLogAt = null
        Log.i(TAG, "Mark requested visible=$visible")
    }

    private fun observe(observation: String) {
        if (session.state.value.markVisible && !Settings.canDrawOverlays(this)) {
            end("Overlay permission revoked")
            return
        }
        session.observe(observation)
        val now = SystemClock.elapsedRealtime()
        if (lastLogAt?.let { now - it < 1000 } != true) {
            lastLogAt = now
            Log.i(TAG, observation)
        }
    }

    private fun notification(projecting: Boolean): Notification {
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(
            NotificationChannel(CHANNEL, getString(R.string.app_name), NotificationManager.IMPORTANCE_LOW),
        )
        val open = PendingIntent.getActivity(
            this, 0, Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_IMMUTABLE,
        )
        val stop = PendingIntent.getService(
            this, 0, Intent(this, CaptureService::class.java).setAction(STOP), PendingIntent.FLAG_IMMUTABLE,
        )
        return Notification.Builder(this, CHANNEL)
            .setSmallIcon(android.R.drawable.ic_menu_camera)
            .setContentTitle(getString(if (projecting) R.string.capture_active else R.string.overlay_active))
            .setContentIntent(open)
            .setOngoing(true)
            .addAction(Notification.Action.Builder(null, getString(R.string.stop), stop).build())
            .build()
    }

    private fun end(reason: String) {
        release(reason)
        stopSelf()
    }

    private fun release(reason: String) {
        if (session.state.value.phase == CapturePhase.Idle) return
        session.stopping()
        val start = SystemClock.elapsedRealtime()
        var captureReleased = false
        var overlayReleased = false
        try {
            capture?.close()
            captureReleased = true
        } catch (failure: RuntimeException) {
            Log.w(TAG, "Capture release failed (${failure.javaClass.simpleName})")
        } finally {
            capture = null
            try {
                overlay.hide()
                overlayReleased = true
            } catch (failure: RuntimeException) {
                Log.w(TAG, "Overlay release failed (${failure.javaClass.simpleName})")
            }
            stopForeground(STOP_FOREGROUND_REMOVE)
            session.finish(reason)
            Log.i(TAG, "$reason captureReleased=$captureReleased overlayRemoved=$overlayReleased " +
                "cleanupMs=${SystemClock.elapsedRealtime() - start}")
        }
    }

    override fun onDestroy() {
        release("Service destroyed")
        super.onDestroy()
    }

    companion object {
        val session = CaptureSession()
        const val START_CAPTURE = "capture"
        const val START_OVERLAY = "overlay"
        const val TOGGLE_MARK = "toggle_mark"
        const val STOP = "stop"
        const val RESULT_CODE = "result_code"
        const val CONSENT = "consent"
        private const val TAG = "FootballTactics"
        private const val CHANNEL = "feasibility"
        private const val NOTIFICATION_ID = 1
    }
}
