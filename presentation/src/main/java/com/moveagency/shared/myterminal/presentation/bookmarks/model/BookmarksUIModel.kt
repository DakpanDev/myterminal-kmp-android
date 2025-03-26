package com.moveagency.shared.myterminal.presentation.bookmarks.model

import com.moveagency.shared.myterminal.presentation.generic.model.FlightUIModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class BookmarksUIModel(
    val flights: ImmutableList<FlightUIModel> = persistentListOf(),
)
