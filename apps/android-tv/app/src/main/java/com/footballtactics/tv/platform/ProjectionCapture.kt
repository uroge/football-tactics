package com.footballtactics.tv.platform

import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.ImageReader
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Handler
import android.os.SystemClock
import android.view.WindowManager
import kotlin.math.roundToInt

internal class ProjectionCapture(
    private val context: Context,
    private val handler: Handler,
    private val onObservation: (String) -> Unit,
    private val onEnded: (String) -> Unit,
) : AutoCloseable {
    private var projection: MediaProjection? = null
    private var display: VirtualDisplay? = null
    private var reader: ImageReader? = null
    private val inspection = FrameInspection()
    private var lastSampleAt: Long? = null
    private val densityDpi = context.resources.displayMetrics.densityDpi

    private val callback = object : MediaProjection.Callback() {
        override fun onStop() {
            if (projection != null) onEnded("System ended capture")
        }

        override fun onCapturedContentResize(width: Int, height: Int) {
            val currentDisplay = display ?: return
            try {
                val next = openReader(width, height)
                try {
                    currentDisplay.resize(next.width, next.height, densityDpi)
                    currentDisplay.surface = next.surface
                } catch (failure: RuntimeException) {
                    next.close()
                    throw failure
                }
                reader?.close()
                reader = next
                resetInspection()
            } catch (failure: RuntimeException) {
                onEnded("Capture resize failed (${failure.javaClass.simpleName})")
            }
        }
    }

    fun start(resultCode: Int, consent: Intent) {
        val manager = context.getSystemService(MediaProjectionManager::class.java)
        val granted = checkNotNull(manager.getMediaProjection(resultCode, consent))
        projection = granted
        granted.registerCallback(callback, handler)

        val (width, height) = displaySize()
        val initialReader = openReader(width, height)
        reader = initialReader
        display = checkNotNull(granted.createVirtualDisplay(
            "Football Tactics", initialReader.width, initialReader.height, densityDpi,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR, initialReader.surface, null, handler,
        ))
    }

    fun resetInspection() {
        inspection.reset()
        lastSampleAt = null
    }

    private fun openReader(width: Int, height: Int): ImageReader {
        val scale = minOf(1f, 960f / maxOf(width, height))
        return ImageReader.newInstance(
            (width * scale).roundToInt().coerceAtLeast(1),
            (height * scale).roundToInt().coerceAtLeast(1),
            PixelFormat.RGBA_8888, 2,
        ).apply {
            setOnImageAvailableListener({ source ->
                if (source !== reader) return@setOnImageAvailableListener
                try {
                    source.acquireLatestImage()?.use { image ->
                        val now = SystemClock.elapsedRealtime()
                        if (lastSampleAt?.let { now - it < 200 } != true) {
                            lastSampleAt = now
                            onObservation(inspection.inspect(image))
                        }
                    }
                } catch (failure: RuntimeException) {
                    onEnded("Frame acquisition failed (${failure.javaClass.simpleName})")
                }
            }, handler)
        }
    }

    @Suppress("DEPRECATION")
    private fun displaySize(): Pair<Int, Int> {
        // Resources are device-wide for this service, including while another app owns focus.
        val metrics = android.util.DisplayMetrics()
        context.getSystemService(WindowManager::class.java).defaultDisplay.getRealMetrics(metrics)
        return metrics.widthPixels to metrics.heightPixels
    }

    override fun close() {
        val activeProjection = projection
        projection = null
        val activeDisplay = display
        display = null
        val activeReader = reader
        reader = null
        resetInspection()
        try {
            activeDisplay?.release()
        } finally {
            try {
                activeReader?.close()
            } finally {
                activeProjection?.unregisterCallback(callback)
                activeProjection?.stop()
            }
        }
    }
}
