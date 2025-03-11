package com.moveagency.myterminal.presentation.bookmarks.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m2mobi.utility.utils.android.extensions.launchCatchingOnIO
import com.m2mobi.utility.utils.android.mvvm.MutableEventFlow
import com.m2mobi.utility.utils.android.mvvm.model.*
import com.m2mobi.utility.utils.android.mvvm.model.TypedUIState.Loading
import com.moveagency.myterminal.domain.bookmarks.ObserveBookmark
import com.moveagency.myterminal.domain.details.BookmarkFlight
import com.moveagency.myterminal.domain.details.UnBookmarkFlight
import com.moveagency.myterminal.presentation.bookmarks.details.model.BookmarkDetailsViewModelArgs
import com.moveagency.myterminal.presentation.generic.mapper.DetailsUIMapper
import com.moveagency.myterminal.presentation.generic.model.DetailsUIModel
import com.moveagency.myterminal.presentation.home.details.navigation.DetailsNavigation
import com.moveagency.myterminal.presentation.home.details.navigation.DetailsNavigation.GoBack
import kotlinx.coroutines.flow.*
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class BookmarkDetailsViewModel(
    @InjectedParam private val args: BookmarkDetailsViewModelArgs,
    private val observeBookmark: ObserveBookmark,
    private val bookmarkFlight: BookmarkFlight,
    private val unBookmarkFlight: UnBookmarkFlight,
    private val mapper: DetailsUIMapper,
) : ViewModel() {

    private val _uiState = MutableStateFlow<TypedUIState<DetailsUIModel, Unit>>(Loading)
    val uiState by lazy {
        retrieveBookmarkedFlight()
        _uiState.asStateFlow()
    }

    private val _navigation = MutableEventFlow<DetailsNavigation>()
    val navigation = _navigation.asEventFlow()

    private fun retrieveBookmarkedFlight() {
        viewModelScope.launchCatchingOnIO(::onRetrieveBookmarkError) {
            observeBookmark(args.flightId).collectLatest {
                it.let(mapper::mapFlightToDetails).also(_uiState::setNormal)
            }
        }
    }

    private fun onRetrieveBookmarkError(throwable: Throwable) {
        Log.e(javaClass.simpleName, "An error occurred: $throwable")
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
        Log.e(javaClass.simpleName, "An error occurred while bookmarking: $throwable")
    }

    fun onRetry() {
        _uiState.setLoading()
        retrieveBookmarkedFlight()
    }

    fun onBackPressed() = _navigation.setEvent(GoBack)
}
