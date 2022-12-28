package ua.ilyadreamix.compose.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.material.Typography

/**
 * Dark and light palettes for TransparentSystemBarsTheme
 * @see TransparentSystemBarsTheme {@link TransparentSystemBarsTheme}
 * @author IlyaDreamix
 */
data class Palette(
    val light: Colors,
    val dark: Colors
)

/**
 * Material theme with transparent system bars
 * @param palette Default app dark and light palettes
 * @param setDarkTheme Apply dark theme
 * @param setSystemBarsTransparent Set system bars transparent
 * @param content Screen content
 * @author IlyaDreamix
 */
@Composable
fun TransparentSystemBarsTheme(
    palette: Palette,
    setDarkTheme: Boolean = isSystemInDarkTheme(),
    setSystemBarsTransparent: Boolean = false,
    typography: Typography = Typography(),
    content: @Composable () -> Unit
) {
    val colors = when (setDarkTheme) {
        true -> palette.dark
        false -> palette.light
    }

    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            if (!setSystemBarsTransparent) {
                window.statusBarColor = colors.background.toArgb()
                window.navigationBarColor = colors.background.toArgb()
            } else {
                WindowCompat.setDecorFitsSystemWindows(window, false)
                window.statusBarColor = Color.Transparent.toArgb()
                window.navigationBarColor = Color.Transparent.toArgb()
            }

            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = !setDarkTheme
            insetsController.isAppearanceLightNavigationBars = !setDarkTheme
        }
    }

    MaterialTheme(
        colors = colors,
        content = content,
        typography = typography
    )
}