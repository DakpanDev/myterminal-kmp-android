package com.moveagency.myterminal.generic.details.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import com.moveagency.myterminal.R
import com.moveagency.myterminal.generic.extension.getColor
import com.moveagency.myterminal.generic.extension.toDisplayString
import com.moveagency.myterminal.presentation.generic.model.FlightStatus
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.colors
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.typography
import com.moveagency.myterminal.ui.theme.Spacing
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.LocalDateTime

@Composable
fun GeneralDetails(
    flightName: String,
    destination: String,
    states: ImmutableList<FlightStatus>,
    departureDateTime: LocalDateTime,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(Spacing.x1),
) {
    val dateString = remember { departureDateTime.date.toDisplayString() }
    val timeString = remember { departureDateTime.time.toString() }

    Text(
        text = stringResource(R.string.details_flight_name, flightName),
        modifier = Modifier.padding(bottom = Spacing.x1),
        color = colors.white,
        style = typography.h1,
    )
    Text(
        text = stringResource(R.string.details_destination, destination),
        color = colors.white,
        style = typography.h2,
    )
    Text(
        text = buildAnnotatedString {
            append(stringResource(R.string.details_status))
            append(" ")
            states.onEachIndexed { index, flightStatus ->
                withStyle(style = SpanStyle(color = flightStatus.getColor())) {
                    append(flightStatus.value)
                }
                if (index < states.lastIndex) append(", ")
            }
        },
        color = colors.white,
        style = typography.h2,
    )
    Text(
        text = stringResource(
            R.string.details_scheduled_departure,
            dateString,
            timeString
        ),
        color = colors.white,
        style = typography.h2,
    )
}
