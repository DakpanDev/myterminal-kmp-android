package com.moveagency.myterminal.generic.extension

import androidx.compose.runtime.Composable
import com.moveagency.myterminal.presentation.generic.model.FlightStatus
import com.moveagency.myterminal.presentation.generic.model.FlightStatus.*
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.colors

@Composable
fun FlightStatus.getColor() = when (this) {
    Scheduled -> colors.statusBlue
    Delayed -> colors.statusYellow
    WaitInLounge -> colors.statusBlue
    GateOpen -> colors.statusGreen
    Boarding -> colors.statusGreen
    GateClosing -> colors.statusOrange
    GateClosed -> colors.statusRed
    Departed -> colors.statusOrange
    Cancelled -> colors.statusRed
    GateChange -> colors.statusYellow
    Tomorrow -> colors.statusBlue
    Unknown -> colors.statusYellow
}
