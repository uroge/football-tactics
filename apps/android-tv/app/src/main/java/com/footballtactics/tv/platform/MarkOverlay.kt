package com.footballtactics.tv.platform

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import kotlin.math.roundToInt

internal class MarkOverlay(private val context: Context) {
    private val windows = context.getSystemService(WindowManager::class.java)
    private var mark: View? = null

    fun show() {
        if (mark != null) return
        val view = object : View(context) {
            private val paint = Paint()

            override fun onDraw(canvas: Canvas) {
                val stripe = width / 3f
                paint.color = Color.MAGENTA
                canvas.drawRect(0f, 0f, stripe, height.toFloat(), paint)
                canvas.drawRect(stripe * 2, 0f, width.toFloat(), height.toFloat(), paint)
                paint.color = Color.CYAN
                canvas.drawRect(stripe, 0f, stripe * 2, height.toFloat(), paint)
            }
        }
        val layout = WindowManager.LayoutParams(
            dp(96), dp(28),
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            PixelFormat.TRANSLUCENT,
        ).apply {
            gravity = Gravity.TOP or Gravity.LEFT
            x = dp(32)
            y = dp(32)
            // Stay below Android's untrusted touch occlusion limit as well as leaving D-pad focus alone.
            alpha = 0.75f
        }
        windows.addView(view, layout)
        mark = view
    }

    fun hide() {
        val view = mark ?: return
        windows.removeViewImmediate(view)
        mark = null
    }

    private fun dp(value: Int) = (value * context.resources.displayMetrics.density).roundToInt()
}
