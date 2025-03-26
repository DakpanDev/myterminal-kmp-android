package com.moveagency.shared.myterminal.ui.theme.color

import androidx.compose.ui.graphics.Color

val myTerminalColorScheme = MyTerminalColors(
    white = ColorPalette.White,
    primaryGray = ColorPalette.PrimaryGray,
    secondaryGray = ColorPalette.SecondaryGray,
    statusBlue = ColorPalette.StatusBlue,
    statusGreen = ColorPalette.StatusGreen,
    statusRed = ColorPalette.StatusRed,
    statusOrange = ColorPalette.StatusOrange,
    statusYellow = ColorPalette.StatusYellow,
)

data class MyTerminalColors(
    val white: Color,
    val primaryGray: Color,
    val secondaryGray: Color,
    val statusBlue: Color,
    val statusGreen: Color,
    val statusRed: Color,
    val statusOrange: Color,
    val statusYellow: Color,
)
