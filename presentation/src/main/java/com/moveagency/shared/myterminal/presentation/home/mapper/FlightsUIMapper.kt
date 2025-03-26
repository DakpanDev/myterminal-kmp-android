package com.moveagency.shared.myterminal.presentation.home.mapper

import com.moveagency.myterminal.domain.generic.model.Flight
import com.moveagency.shared.myterminal.presentation.generic.model.FlightUIModel
import com.moveagency.shared.myterminal.presentation.home.model.FlightListUIModel
import kotlinx.collections.immutable.toPersistentList
import org.koin.core.annotation.Factory

@Factory
class FlightsUIMapper {

    fun mapFlightListToUiModel(flights: List<Flight>) = FlightListUIModel(
        flights = flights.map(::mapFlightToUiModel).toPersistentList(),
    )

    fun mapFlightToUiModel(flight: Flight) = FlightUIModel(
        id = flight.id,
        name = flight.name,
        destination = flight.destination,
        date = flight.departureDateTime.date,
    )
}
