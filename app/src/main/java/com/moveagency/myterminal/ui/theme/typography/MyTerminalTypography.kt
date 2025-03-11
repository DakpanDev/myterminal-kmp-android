package com.moveagency.myterminal.ui.theme.typography

import androidx.compose.ui.text.TextStyle

val myTerminalTypography = MyTerminalTypography(
    h1 = TextStyles.H1,
    h2 = TextStyles.H2,
    body = TextStyles.Body,
    bodySmall = TextStyles.BodySmall,
)

data class MyTerminalTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val body: TextStyle,
    val bodySmall: TextStyle,
)
