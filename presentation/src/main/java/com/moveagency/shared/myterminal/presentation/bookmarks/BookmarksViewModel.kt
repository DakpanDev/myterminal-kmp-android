package com.moveagency.shared.myterminal.presentation.bookmarks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m2mobi.utility.utils.android.extensions.launchCatchingOnIO
import com.m2mobi.utility.utils.android.mvvm.MutableEventFlow
import com.moveagency.myterminal.domain.bookmarks.ObserveAllBookmarks
import com.moveagency.shared.myterminal.presentation.bookmarks.model.BookmarksNavigation
import com.moveagency.shared.myterminal.presentation.bookmarks.model.BookmarksNavigation.OpenDetails
import com.moveagency.shared.myterminal.presentation.bookmarks.model.BookmarksUIModel
import com.moveagency.shared.myterminal.presentation.home.mapper.FlightsUIMapper
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.*
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class BookmarksViewModel(
    private val observeAllBookmarks: ObserveAllBookmarks,
    private val mapper: FlightsUIMapper,
) : ViewModel() {

    private val _uiModel = MutableStateFlow(BookmarksUIModel())
    val uiModel by lazy {
        retrieveBookmarks()
        _uiModel.asStateFlow()
    }

    private val _navigation = MutableEventFlow<BookmarksNavigation>()
    val navigation = _navigation.asEventFlow()

    private fun retrieveBookmarks() {
        viewModelScope.launchCatchingOnIO(::onGetBookmarksError) {
            observeAllBookmarks().collectLatest { flightList ->
                flightList
                    .filter { it.isBookmarked }
                    .map(mapper::mapFlightToUiModel).also { newState ->
                        _uiModel.update { it.copy(flights = newState.toPersistentList()) }
                    }
            }
        }
    }

    private fun onGetBookmarksError(throwable: Throwable) {
        Log.e(javaClass.simpleName, "An error occurred while retrieving bookmarks: $throwable")
    }

    fun onFlightClicked(id: String) = _navigation.setEvent(OpenDetails(id))
}
