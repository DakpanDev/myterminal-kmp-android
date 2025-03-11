package com.moveagency.myterminal.domain.home

import com.moveagency.myterminal.domain.generic.repository.FlightsRepository
import org.koin.core.annotation.Factory
import java.time.LocalDate

@Factory
class ObserveFlights(
    private val repository: FlightsRepository,
) {

    suspend operator fun invoke(day: LocalDate = LocalDate.now()) = repository.observeFlights(day)
}
