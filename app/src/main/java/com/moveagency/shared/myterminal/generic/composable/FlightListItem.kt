package com.moveagency.shared.myterminal.generic.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moveagency.myterminal.domain.generic.util.now
import com.moveagency.shared.myterminal.generic.extension.toDisplayString
import com.moveagency.shared.myterminal.presentation.generic.model.FlightUIModel
import com.moveagency.shared.myterminal.ui.theme.MyTerminalTheme
import com.moveagency.shared.myterminal.ui.theme.MyTerminalTheme.colors
import com.moveagency.shared.myterminal.ui.theme.MyTerminalTheme.typography
import com.moveagency.shared.myterminal.ui.theme.Spacing
import kotlinx.datetime.LocalDate

@Composable
fun FlightListItem(
    uiModel: FlightUIModel,
    modifier: Modifier = Modifier,
) {
    if (uiModel.isQueried) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(Spacing.x0_5))
                .border(width = 1.dp, color = colors.white, shape = RoundedCornerShape(Spacing.x0_5))
                .background(colors.primaryGray)
                .padding(Spacing.x1),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.x1),
            ) {
                Text(
                    text = uiModel.name,
                    color = colors.white,
                    style = typography.h2,
                )
                Text(
                    text = uiModel.destination,
                    color = colors.white,
                    style = typography.body,
                )
            }
            Text(
                text = uiModel.date.toDisplayString(),
                color = colors.white,
                style = typography.body,
            )
        }
    }
}

@Composable
@Preview
private fun PreviewFlightListItem() = MyTerminalTheme {
    val today = LocalDate.now()
    val uiModel = FlightUIModel(
        id = "1",
        name = "HV6935",
        destination = "TIA",
        date = today
    )
    FlightListItem(
        uiModel = uiModel,
        modifier = Modifier.fillMaxWidth(),
    )
}
