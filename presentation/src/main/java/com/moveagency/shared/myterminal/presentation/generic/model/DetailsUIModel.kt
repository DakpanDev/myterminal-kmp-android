package com.moveagency.shared.myterminal.presentation.generic.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

data class DetailsUIModel(
    val id: String,
    val name: String,
    val destination: String,
    val states: ImmutableList<FlightStatus>,
    val departureDateTime: LocalDateTime,
    val terminal: Int?,
    val checkinRows: ImmutableList<String>,
    val gate: String?,
    val checkinClosingTime: LocalTime?,
    val gateOpeningTime: LocalTime?,
    val boardingTime: LocalTime?,
    val actualDepartureTime: LocalTime?,
    val lastUpdated: LocalDateTime,
    val isBookmarked: Boolean,
)
