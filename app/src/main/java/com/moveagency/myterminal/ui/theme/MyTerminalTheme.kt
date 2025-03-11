package com.moveagency.myterminal.ui.theme

import androidx.compose.runtime.*
import com.moveagency.myterminal.ui.theme.color.MyTerminalColors
import com.moveagency.myterminal.ui.theme.typography.MyTerminalTypography

val LocalMyTerminalColors = staticCompositionLocalOf<MyTerminalColors> { error("MyTerminal colors not set") }
val LocalMyTerminalTypography = staticCompositionLocalOf<MyTerminalTypography> { error("MyTerminal typography not set") }

object MyTerminalTheme {

    val colors: MyTerminalColors
        @Composable
        @ReadOnlyComposable
        get() = LocalMyTerminalColors.current

    val typography: MyTerminalTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalMyTerminalTypography.current
}
