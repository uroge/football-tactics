package com.footballtactics.tv.platform

import android.media.Image
import kotlin.math.abs

/** Aggregates a sparse in-memory sample. No frames, hashes or pixel values are stored or logged. */
internal class FrameInspection {
    private var previousLuma: IntArray? = null

    fun reset() {
        previousLuma = null
    }

    fun inspect(image: Image): String {
        val plane = image.planes.single()
        val pixels = plane.buffer
        val crop = image.cropRect
        val step = (crop.width() / 240).coerceAtLeast(1)
        val luma = IntArray(32 * 18)
        var lit = 0
        var changed = 0
        for (index in luma.indices) {
            // Center the samples within cells and keep them inside the actual crop and stride.
            val x = crop.left + (index % 32 * 2 + 1) * crop.width() / 64
            val y = crop.top + (index / 32 * 2 + 1) * crop.height() / 36
            val offset = y * plane.rowStride + x * plane.pixelStride
            val red = pixels.get(offset).toInt() and 255
            val green = pixels.get(offset + 1).toInt() and 255
            val blue = pixels.get(offset + 2).toInt() and 255
            val value = (red + 2 * green + blue) / 4
            luma[index] = value
            if (value > 20) lit++
            if (abs(value - (previousLuma?.get(index) ?: value)) > 16) changed++
        }
        val movement = previousLuma?.let { "${changed * 100 / luma.size}%" } ?: "baseline"
        previousLuma = luma

        var markDetected = false
        // Scan for the magenta/cyan/magenta signature, including inside letterboxed captures.
        search@ for (y in crop.top until crop.bottom step step) {
            var previousColor = 0
            var stripes = 0
            for (x in crop.left until crop.right step step) {
                val offset = y * plane.rowStride + x * plane.pixelStride
                val red = pixels.get(offset).toInt() and 255
                val green = pixels.get(offset + 1).toInt() and 255
                val blue = pixels.get(offset + 2).toInt() and 255
                val color = when {
                    blue > 140 && red - green > 105 -> 1
                    blue > 140 && green - red > 105 -> 2
                    else -> 0
                }
                if (color == previousColor) continue
                stripes = when {
                    stripes == 1 && color == 2 -> 2
                    stripes == 2 && color == 1 -> 3
                    color == 1 -> 1
                    else -> 0
                }
                if (stripes == 3) {
                    markDetected = true
                    break@search
                }
                previousColor = color
            }
        }
        return "${crop.width()}x${crop.height()} lit=${lit * 100 / luma.size}% motion=$movement mark=$markDetected"
    }
}
