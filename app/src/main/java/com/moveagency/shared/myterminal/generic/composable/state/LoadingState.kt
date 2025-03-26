package com.moveagency.shared.myterminal.generic.composable.state

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moveagency.shared.myterminal.ui.theme.MyTerminalTheme.colors

@Composable
fun LoadingState(
    modifier: Modifier = Modifier,
) = Box(
    modifier = modifier,
    contentAlignment = Alignment.Center,
) {
    CircularProgressIndicator(color = colors.white)
}
