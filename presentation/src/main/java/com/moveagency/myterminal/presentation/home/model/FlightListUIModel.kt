package com.moveagency.myterminal.presentation.home.model

import com.moveagency.myterminal.presentation.generic.model.FlightUIModel
import kotlinx.collections.immutable.ImmutableList

data class FlightListUIModel(
    val flights: ImmutableList<FlightUIModel>,
    val newFlightsLoading: Boolean = false,
    val searchQuery: String = "",
)
