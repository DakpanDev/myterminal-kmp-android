package com.moveagency.myterminal.data.generic.mapper

import com.moveagency.myterminal.data.generic.remote.response.*
import com.moveagency.myterminal.domain.generic.model.Flight
import org.koin.core.annotation.Factory
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Factory
class FlightsResponseMapper {

    fun mapListResponseToDomain(response: FlightListResponse) = response.flights
        ?.mapNotNull(::mapResponseToDomain)
        ?: listOf()

    private fun mapResponseToDomain(response: FlightDetailsResponse): Flight? {
        return Flight(
            id = response.id ?: return null,
            name = response.flightName ?: return null,
            destination = response.route?.destinations?.last() ?: return null,
            states = response.status?.flightStates,
            departureDateTime = response.departureDateTime?.let(::mapStringToDateTime) ?: return null,
            terminal = response.terminal,
            checkinRows = response.checkinRows?.let(::mapCheckinAllocationsToPositions),
            gate = response.gate,
            checkinClosingTime = response.checkinRows?.allocations
                ?.firstOrNull()
                ?.endTime
                ?.let { mapStringToDateTime(it).toLocalTime() },
            gateOpeningTime = response.gateOpeningTime?.let { mapStringToDateTime(it).toLocalTime() },
            boardingTime = response.boardingTime?.let { mapStringToDateTime(it).toLocalTime() },
            actualDepartureTime = response.actualDepartureTime?.let { mapStringToDateTime(it).toLocalTime() },
            lastUpdated = response.lastUpdated?.let(::mapStringToDateTime) ?: return null,
            isBookmarked = false,
        )
    }

    private fun mapStringToDateTime(value: String): LocalDateTime {
        val offsetDateTime = OffsetDateTime.parse(value)
        return offsetDateTime.toLocalDateTime()
    }

    private fun mapCheckinAllocationsToPositions(response: CheckinAllocationsResponse): List<String>? {
        return response.allocations
            ?.mapNotNull { it.rows }
            ?.mapNotNull { it.rows }
            ?.flatten()
            ?.mapNotNull { it.position }
            ?: return null
    }
}
