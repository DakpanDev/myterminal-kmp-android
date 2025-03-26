package com.moveagency.shared.myterminal.presentation.generic.model

import kotlinx.datetime.LocalDate

data class FlightUIModel(
    val id: String,
    val name: String,
    val destination: String,
    val date: LocalDate,
    val isQueried: Boolean = true,
)
