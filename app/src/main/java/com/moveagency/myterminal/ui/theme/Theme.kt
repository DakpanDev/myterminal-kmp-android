package com.moveagency.myterminal.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.moveagency.myterminal.ui.theme.color.myTerminalColorScheme
import com.moveagency.myterminal.ui.theme.typography.myTerminalTypography

@Composable
fun MyTerminalTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        CompositionLocalProvider(
            LocalMyTerminalColors provides myTerminalColorScheme,
            LocalMyTerminalTypography provides myTerminalTypography,
            content = content,
        )
    }
}
