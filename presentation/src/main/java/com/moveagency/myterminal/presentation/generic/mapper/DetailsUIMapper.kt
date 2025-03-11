package com.moveagency.myterminal.presentation.generic.mapper

import com.moveagency.myterminal.domain.generic.model.Flight
import com.moveagency.myterminal.presentation.generic.model.DetailsUIModel
import com.moveagency.myterminal.presentation.generic.model.FlightStatus
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import org.koin.core.annotation.Factory

@Factory
class DetailsUIMapper {

    fun mapFlightToDetails(flight: Flight) = DetailsUIModel(
        id = flight.id,
        name = flight.name,
        destination = flight.destination,
        states = flight.states?.map(::mapStateToFlightStatus)?.toImmutableList()
            ?: persistentListOf(),
        departureDateTime = flight.departureDateTime,
        terminal = flight.terminal,
        checkinRows = flight.checkinRows?.toImmutableList() ?: persistentListOf(),
        gate = flight.gate,
        checkinClosingTime = flight.checkinClosingTime,
        gateOpeningTime = flight.gateOpeningTime,
        boardingTime = flight.boardingTime,
        actualDepartureTime = flight.actualDepartureTime,
        lastUpdated = flight.lastUpdated,
        isBookmarked = flight.isBookmarked,
    )

    private fun mapStateToFlightStatus(state: String) = when (state) {
        FlightScheduled -> FlightStatus.Scheduled
        Delayed -> FlightStatus.Delayed
        WaitInLounge -> FlightStatus.WaitInLounge
        GateOpen -> FlightStatus.GateOpen
        Boarding -> FlightStatus.Boarding
        GateClosing -> FlightStatus.GateClosing
        GateClosed -> FlightStatus.GateClosed
        Departed -> FlightStatus.Departed
        Cancelled -> FlightStatus.Cancelled
        GateChange -> FlightStatus.GateChange
        Tomorrow -> FlightStatus.Tomorrow
        else -> FlightStatus.Unknown
    }

    companion object {

        private val FlightScheduled = "SCH"
        private val Delayed = "DEL"
        private val WaitInLounge = "WIL"
        private val GateOpen = "GTO"
        private val Boarding = "BRD"
        private val GateClosing = "GCL"
        private val GateClosed = "GTD"
        private val Departed = "DEP"
        private val Cancelled = "CNX"
        private val GateChange = "GCH"
        private val Tomorrow = "TOM"
    }
}
