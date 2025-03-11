package com.moveagency.myterminal.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m2mobi.utility.utils.android.extensions.launchCatchingOnIO
import com.m2mobi.utility.utils.android.mvvm.MutableEventFlow
import com.m2mobi.utility.utils.android.mvvm.model.*
import com.m2mobi.utility.utils.android.mvvm.model.TypedUIState.Loading
import com.m2mobi.utility.utils.coroutines.SerialJob
import com.moveagency.myterminal.domain.home.FetchMoreFlights
import com.moveagency.myterminal.domain.home.ObserveFlights
import com.moveagency.myterminal.presentation.generic.model.FlightUIModel
import com.moveagency.myterminal.presentation.home.mapper.FlightsUIMapper
import com.moveagency.myterminal.presentation.home.model.FlightListUIModel
import com.moveagency.myterminal.presentation.home.navigation.HomeNavigation
import com.moveagency.myterminal.presentation.home.navigation.HomeNavigation.OpenDetailPage
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel
import java.time.LocalDate

@KoinViewModel
class HomeViewModel(
    private val observeFlights: ObserveFlights,
    private val fetchMoreFlights: FetchMoreFlights,
    private val mapper: FlightsUIMapper,
) : ViewModel() {

    private val _uiState = MutableStateFlow<TypedUIState<FlightListUIModel, Unit>>(Loading)
    val uiState by lazy {
        observeFlightsByDay(LocalDate.now())
        _uiState.asStateFlow()
    }

    private val _navigation = MutableEventFlow<HomeNavigation>()
    val navigation = _navigation.asEventFlow()

    private val observeJob = SerialJob()

    private fun observeFlightsByDay(day: LocalDate) {
        observeJob.launchCatchingOnIO(::onFetchFlightsError) {
            observeFlights(day).collect {
                val uiModel = mapper.mapFlightListToUiModel(it)
                _uiState.setNormal(uiModel)
            }
        }
    }

    fun onDateChanged(newDate: LocalDate) {
        viewModelScope.launchCatchingOnIO(::onFetchFlightsError) {
            _uiState.setLoading()
            observeFlightsByDay(newDate)
        }
    }

    private fun onFetchFlightsError(throwable: Throwable) {
        Log.e(javaClass.simpleName, "An error occurred while fetching flights: $throwable")
        _uiState.setError()
    }

    fun onRetry(day: LocalDate = LocalDate.now()) {
        _uiState.setLoading()
        observeFlightsByDay(day)
    }

    fun onFlightClicked(id: String) = _navigation.setEvent(OpenDetailPage(id))

    fun onSearch(query: String) {
        _uiState.value.normalDataOrNull()?.let { data ->
            val updatedWithQuery = data.flights.map {
                it.copy(isQueried = compliesWithQuery(query, it))
            }.toPersistentList()
            val newUiModel = data.copy(flights = updatedWithQuery, searchQuery = query)

            _uiState.setNormal(newUiModel)
        }
    }

    private fun compliesWithQuery(query: String, flight: FlightUIModel): Boolean {
        val upperQuery = query.uppercase()
        val name = flight.name.uppercase()
        val destination = flight.destination.uppercase()
        return upperQuery in name || upperQuery in destination
    }

    fun onLoadMore() {
        viewModelScope.launchCatchingOnIO {
            _uiState.value.normalDataOrNull()?.let {
                _uiState.setNormal(it.copy(newFlightsLoading = true))
                val date = it.flights.firstOrNull()?.date ?: LocalDate.now()
                fetchMoreFlights(date)
                _uiState.setNormal(it.copy(newFlightsLoading = false))
            }
        }
    }
}
