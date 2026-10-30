package io.github.jqssun.airplay

import android.content.Context
import android.os.Build
import android.provider.Settings

/**
 * Castiqq: the name iPhones list this TV under when none is set, the one the
 * TV itself shows in its settings (such as "Living Room Fire TV"), like
 * Castiqq TV uses.
 */
fun deviceName(context: Context): String =
    Settings.Global.getString(context.contentResolver, Settings.Global.DEVICE_NAME)
        ?.takeIf { it.isNotBlank() }
        ?: Build.MODEL
