package com.moveagency.myterminal.presentation.home.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m2mobi.utility.utils.android.extensions.launchCatchingOnIO
import com.m2mobi.utility.utils.android.mvvm.MutableEventFlow
import com.m2mobi.utility.utils.android.mvvm.model.*
import com.m2mobi.utility.utils.android.mvvm.model.TypedUIState.Loading
import com.moveagency.myterminal.domain.details.*
import com.moveagency.myterminal.presentation.generic.mapper.DetailsUIMapper
import com.moveagency.myterminal.presentation.generic.model.DetailsUIModel
import com.moveagency.myterminal.presentation.generic.model.DetailsViewModelArgs
import com.moveagency.myterminal.presentation.home.details.navigation.DetailsNavigation
import com.moveagency.myterminal.presentation.home.details.navigation.DetailsNavigation.GoBack
import kotlinx.coroutines.flow.*
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class DetailsViewModel(
    @InjectedParam private val args: DetailsViewModelArgs,
    private val observeFlightDetails: ObserveFlightDetails,
    private val bookmarkFlight: BookmarkFlight,
    private val unBookmarkFlight: UnBookmarkFlight,
    private val mapper: DetailsUIMapper,
) : ViewModel() {

    private val _uiState = MutableStateFlow<TypedUIState<DetailsUIModel, Unit>>(Loading)
    val uiState by lazy {
        retrieveDetails()
        _uiState.asStateFlow()
    }

    private val _navigation = MutableEventFlow<DetailsNavigation>()
    val navigation = _navigation.asEventFlow()

    private fun retrieveDetails() {
        viewModelScope.launchCatchingOnIO(::onRetrieveDetailsError) {
            observeFlightDetails(args.flightId).collectLatest {
                _uiState.setNormal(mapper.mapFlightToDetails(it))
            }
        }
    }

    private fun onRetrieveDetailsError(throwable: Throwable) {
        Log.e(javaClass.simpleName, "An error occurred: $throwable")
        _uiState.setError()
    }

    fun onRetry() {
        _uiState.setLoading()
        retrieveDetails()
    }

    fun onBookmark() {
        viewModelScope.launchCatchingOnIO(::onBookmarkError) {
            _uiState.value.normalDataOrNull()?.let {
                if (it.isBookmarked) {
                    unBookmarkFlight(it.id)
                } else {
                    bookmarkFlight(it.id)
                }
            }
        }
    }

    private fun onBookmarkError(throwable: Throwable) {
        Log.e(javaClass.simpleName, "An error occurred while bookmarking flight: $throwable")
    }

    fun onBackPressed() = _navigation.setEvent(GoBack)
}
