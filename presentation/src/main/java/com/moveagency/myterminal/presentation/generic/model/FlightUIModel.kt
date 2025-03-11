package com.moveagency.myterminal.presentation.generic.model

import java.time.LocalDate

data class FlightUIModel(
    val id: String,
    val name: String,
    val destination: String,
    val date: LocalDate,
    val isQueried: Boolean = true,
)
