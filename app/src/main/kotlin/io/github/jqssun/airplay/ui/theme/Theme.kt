package io.github.jqssun.airplay.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Castiqq's colors: the logo's blue and cyan (docs/brand in the Castiqq repo)
// on the dark grey of Castiqq TV's screens, so this app looks like the rest.
object Castiqq {
    val Blue = Color(0xFF377CF2)
    val BlueDark = Color(0xFF2053DB)
    val Cyan = Color(0xFF8BE3FF)
    val Focus = Color(0xFF60A5FA)
    /** Filled buttons and the selected tab, as in the phone and desktop apps. */
    val Button = Color(0xFF2563EB)
    val Ready = Color(0xFF22C55E)
    val Background = Color(0xFF111827)
    val Surface = Color(0xFF1F2937)
    val Border = Color(0xFF374151)
    val Muted = Color(0xFF9CA3AF)
    val Text = Color(0xFFD1D5DB)
    val Error = Color(0xFFFCA5A5)
}

private val CastiqqColors = darkColorScheme(
    primary = Castiqq.Button,
    onPrimary = Color.White,
    primaryContainer = Castiqq.BlueDark,
    onPrimaryContainer = Color.White,
    secondary = Castiqq.Cyan,
    onSecondary = Castiqq.Background,
    secondaryContainer = Castiqq.Border,
    onSecondaryContainer = Color.White,
    tertiary = Castiqq.Focus,
    onTertiary = Castiqq.Background,
    background = Castiqq.Background,
    onBackground = Color.White,
    surface = Castiqq.Background,
    onSurface = Color.White,
    surfaceVariant = Castiqq.Surface,
    onSurfaceVariant = Castiqq.Muted,
    surfaceContainerLowest = Castiqq.Background,
    surfaceContainerLow = Castiqq.Surface,
    surfaceContainer = Castiqq.Surface,
    surfaceContainerHigh = Castiqq.Surface,
    surfaceContainerHighest = Castiqq.Border,
    outline = Castiqq.Border,
    outlineVariant = Castiqq.Border,
    error = Castiqq.Error,
    onError = Castiqq.Background,
)

/** Always Castiqq's dark look, whatever the system theme or wallpaper colors. */
@Composable
fun AirPlayTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = CastiqqColors, content = content)
}
