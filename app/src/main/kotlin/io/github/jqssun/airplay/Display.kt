package io.github.jqssun.airplay

import android.content.Context
import android.hardware.display.DisplayManager
import android.view.Display

// physical panel resolution. Castiqq: the largest mode the screen supports, not
// the current one: a 4K TV often runs its menus at 1080p (Fire TV does), and
// asking iPhones for 1080p then leaves a portrait iPhone only 1080 pixels tall.
fun Context.realDisplaySize(): Pair<Int, Int> {
    val display = getSystemService(DisplayManager::class.java)
        ?.getDisplay(Display.DEFAULT_DISPLAY)
    val mode = display?.supportedModes?.maxByOrNull { it.physicalWidth * it.physicalHeight } ?: display?.mode
    return if (mode != null) mode.physicalWidth to mode.physicalHeight
    else resources.displayMetrics.let { it.widthPixels to it.heightPixels }
}
