package com.footballtactics.tv.platform

import android.graphics.Color
import android.media.Image
import kotlin.math.abs

/** Aggregates an in-memory sample. No frames, hashes or pixel values are stored or logged. */
internal class FrameInspection {
    private var previousLuma: IntArray? = null

    fun reset() {
        previousLuma = null
    }

    fun inspect(image: Image): String {
        val plane = image.planes.single()
        val pixels = plane.buffer
        val crop = image.cropRect
        val luma = IntArray(32 * 18)
        var lit = 0
        var changed = 0

        fun pixelAt(x: Int, y: Int): Int {
            val offset = y * plane.rowStride + x * plane.pixelStride
            return Color.rgb(
                pixels.get(offset).toInt() and 255,
                pixels.get(offset + 1).toInt() and 255,
                pixels.get(offset + 2).toInt() and 255,
            )
        }

        for (index in luma.indices) {
            val x = crop.left + (index % 32 * 2 + 1) * crop.width() / 64
            val y = crop.top + (index / 32 * 2 + 1) * crop.height() / 36
            val pixel = pixelAt(x, y)
            val value = (Color.red(pixel) + 2 * Color.green(pixel) + Color.blue(pixel)) / 4
            luma[index] = value
            if (value > 20) lit++
            if (abs(value - (previousLuma?.get(index) ?: value)) > 16) changed++
        }
        val movement = previousLuma?.let { "${changed * 100 / luma.size}%" } ?: "baseline"
        previousLuma = luma

        val step = (crop.width() / 240).coerceAtLeast(1)
        var markCandidate = false
        // Look for three adjacent, similarly sized vertical stripes, across several scanlines.
        // This is diagnostic evidence. Compare the same scene with the mark off and on.
        search@ for (y in crop.top until crop.bottom - 2 * step step step) {
            for (x in crop.left until crop.right - 2 * step step step) {
                if (stripeColor(pixelAt(x, y)) != StripeColor.Magenta ||
                    stripeColor(pixelAt(x + step, y)) != StripeColor.Magenta ||
                    stripeColor(pixelAt(x + 2 * step, y)) != StripeColor.Cyan
                ) continue
                var cyanEnd = x + 2 * step
                while (cyanEnd < crop.right && stripeColor(pixelAt(cyanEnd, y)) == StripeColor.Cyan) {
                    cyanEnd += step
                }
                val cyanWidth = cyanEnd - (x + 2 * step)
                if (cyanWidth < 2 * step) continue
                val left = x + 2 * step - cyanWidth / 2
                val right = cyanEnd + cyanWidth / 2
                if (left < crop.left || right >= crop.right) continue
                val middle = cyanEnd - cyanWidth / 2
                if ((0..2).all { row ->
                        val sampleY = y + row * step
                        stripeColor(pixelAt(left, sampleY)) == StripeColor.Magenta &&
                            stripeColor(pixelAt(middle, sampleY)) == StripeColor.Cyan &&
                            stripeColor(pixelAt(right, sampleY)) == StripeColor.Magenta
                    }) {
                    markCandidate = true
                    break@search
                }
            }
        }
        return "${crop.width()}x${crop.height()} lit=${lit * 100 / luma.size}% motion=$movement markCandidate=$markCandidate"
    }

    private enum class StripeColor { Magenta, Cyan, Other }

    private fun stripeColor(pixel: Int): StripeColor {
        val red = Color.red(pixel)
        val green = Color.green(pixel)
        return when {
            Color.blue(pixel) < 140 -> StripeColor.Other
            red - green > 105 -> StripeColor.Magenta
            green - red > 105 -> StripeColor.Cyan
            else -> StripeColor.Other
        }
    }
}
