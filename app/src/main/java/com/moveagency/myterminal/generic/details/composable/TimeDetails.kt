package com.moveagency.myterminal.generic.details.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.moveagency.myterminal.R
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.colors
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.typography
import com.moveagency.myterminal.ui.theme.Spacing
import java.time.LocalTime

@Composable
fun TimeDetails(
    checkInClose: LocalTime?,
    gateOpening: LocalTime?,
    boardingTime: LocalTime?,
    actualDeparture: LocalTime?,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(Spacing.x1),
) {
    TimeRow(
        title = stringResource(R.string.details_checkin_close),
        time = checkInClose,
        modifier = Modifier.fillMaxWidth(),
    )
    TimeRow(
        title = stringResource(R.string.details_gate_open),
        time = gateOpening,
        modifier = Modifier.fillMaxWidth(),
    )
    TimeRow(
        title = stringResource(R.string.details_boarding_time),
        time = boardingTime,
        modifier = Modifier.fillMaxWidth(),
    )
    TimeRow(
        title = stringResource(R.string.details_actual_departure),
        time = actualDeparture,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun TimeRow(
    title: String,
    time: LocalTime?,
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
) {
    val unknownText = stringResource(R.string.generic_unknown)

    Text(
        text = title,
        color = colors.white,
        style = typography.body,
    )
    Text(
        text = time?.toString() ?: unknownText,
        color = colors.white,
        style = typography.body,
    )
}
