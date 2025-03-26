package com.moveagency.shared.myterminal.presentation.bookmarks.model

sealed interface BookmarksNavigation {

    data class OpenDetails(val flightId: String) : BookmarksNavigation
}
