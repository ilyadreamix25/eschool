package com.eschool.android.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.eschool.android.R

@Stable val ESchoolTypography = Typography(
    defaultFontFamily = FontFamily(
        Font(R.font.e_ukraine_bold, FontWeight.Bold),
        Font(R.font.e_ukraine_regular, FontWeight.Normal)
    )
)