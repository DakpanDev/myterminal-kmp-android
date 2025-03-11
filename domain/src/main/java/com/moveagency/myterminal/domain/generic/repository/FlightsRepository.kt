package com.moveagency.myterminal.domain.generic.repository

import com.moveagency.myterminal.domain.generic.model.Flight
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface FlightsRepository {

    suspend fun fetchFlights(date: LocalDate = LocalDate.now())
    suspend fun observeFlights(date: LocalDate): Flow<List<Flight>>
    fun observeFlightDetails(id: String): Flow<Flight>

    fun observeAllBookmarks(): Flow<List<Flight>>
    fun observeBookmark(id: String): Flow<Flight>
    fun bookmarkFlight(id: String)
    fun unBookmarkFlight(id: String)
}
