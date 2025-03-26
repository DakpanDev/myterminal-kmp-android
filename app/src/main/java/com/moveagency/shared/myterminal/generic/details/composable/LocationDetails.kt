package com.moveagency.shared.myterminal.generic.details.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.moveagency.shared.myterminal.R
import com.moveagency.shared.myterminal.ui.theme.MyTerminalTheme.colors
import com.moveagency.shared.myterminal.ui.theme.MyTerminalTheme.typography
import com.moveagency.shared.myterminal.ui.theme.Spacing
import kotlinx.collections.immutable.ImmutableList

@Composable
fun LocationDetails(
    terminal: Int?,
    checkInRows: ImmutableList<String>,
    gate: String?,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(Spacing.x1),
) {
    val unknownText = stringResource(R.string.generic_unknown)
    val checkInText = remember {
        if (checkInRows.isEmpty()) unknownText else checkInRows.joinToString(separator = " & ")
    }

    HorizontalDivider()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.x3),
    ) {
        Text(
            text = stringResource(
                R.string.details_terminal,
                terminal?.toString() ?: unknownText,
            ),
            modifier = Modifier.weight(1F),
            color = colors.white,
            textAlign = TextAlign.Center,
            style = typography.body,
        )
        Text(
            text = stringResource(R.string.details_checkin, checkInText),
            modifier = Modifier.weight(1F),
            color = colors.white,
            textAlign = TextAlign.Center,
            style = typography.body,
        )
        Text(
            text = stringResource(R.string.details_gate, gate ?: unknownText),
            modifier = Modifier.weight(1F),
            color = colors.white,
            textAlign = TextAlign.Center,
            style = typography.body,
        )
    }
    HorizontalDivider()
}
