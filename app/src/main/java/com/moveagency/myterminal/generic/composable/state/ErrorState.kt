package com.moveagency.myterminal.generic.composable.state

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moveagency.myterminal.R
import com.moveagency.myterminal.generic.composable.PrimaryButton
import com.moveagency.myterminal.ui.theme.MyTerminalTheme
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.colors
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.typography
import com.moveagency.myterminal.ui.theme.Spacing

@Composable
fun ErrorState(
    text: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(Spacing.x2)
) {
    Icon(
        painter = painterResource(R.drawable.ic_error),
        contentDescription = null,
        modifier = Modifier.size(80.dp),
        tint = colors.white,
    )
    Text(
        text = text,
        color = colors.white,
        textAlign = TextAlign.Center,
        style = typography.h2,
    )
    PrimaryButton(
        text = stringResource(R.string.generic_retry),
        onClick = onRetry,
    )
}

@Composable
@Preview
private fun PreviewErrorState() = MyTerminalTheme {
    ErrorState(
        text = stringResource(R.string.retrieve_departures_error),
        onRetry = {},
    )
}
