package com.eschool.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush

object BackgroundUtility {
    private val darkGradient = Brush.verticalGradient(
        listOf(
            ESchoolDarkBackgroundStart,
            ESchoolDarkBackgroundEnd,
        )
    )
    private val lightGradient = Brush.verticalGradient(
        listOf(
            ESchoolLightBackgroundStart,
            ESchoolLightBackgroundEnd
        )
    )

    @Composable
    fun getGradient() = if (isSystemInDarkTheme()) darkGradient else lightGradient
}