package com.moveagency.shared.myterminal.presentation.home.model

import com.moveagency.shared.myterminal.presentation.generic.model.FlightUIModel
import kotlinx.collections.immutable.ImmutableList

data class FlightListUIModel(
    val flights: ImmutableList<FlightUIModel>,
    val newFlightsLoading: Boolean = false,
    val searchQuery: String = "",
)
