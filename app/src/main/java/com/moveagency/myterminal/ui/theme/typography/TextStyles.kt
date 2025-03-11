package com.moveagency.myterminal.ui.theme.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.moveagency.myterminal.R

object TextStyles {

    private val baseTextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.kode_mono))
    )

    val H1 = baseTextStyle.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp,
    )

    val H2 = baseTextStyle.copy(
        fontSize = 20.sp,
        lineHeight = 26.sp,
    )

    val Body = baseTextStyle.copy(
        fontSize = 16.sp,
        lineHeight = 20.sp,
    )

    val BodySmall = baseTextStyle.copy(
        fontSize = 12.sp,
        lineHeight = 15.sp,
    )
}
