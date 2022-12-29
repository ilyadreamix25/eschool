package com.eschool.android.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ua.ilyadreamix.compose.ui.theme.Palette
import ua.ilyadreamix.compose.ui.theme.TransparentSystemBarsTheme

private val appPalette = Palette(
    light = lightColors(
        primary = ESchoolBlue,
        background = Color.White,
        onPrimary = Color.White
    ),
    dark = darkColors(
        primary = ESchoolBlue,
        background = Color.Black,
        onPrimary = Color.White
    )
)

@Composable
fun ESchoolTheme(content: @Composable () -> Unit) {
    TransparentSystemBarsTheme(
        palette = appPalette,
        setSystemBarsTransparent = true,
        typography = ESchoolTypography
    ) { content() }
}