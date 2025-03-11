package com.moveagency.myterminal.bookmarks.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.moveagency.myterminal.generic.details.DetailsScreenContent
import com.moveagency.myterminal.generic.details.navigation.HandleNavigation
import com.moveagency.myterminal.presentation.bookmarks.details.BookmarkDetailsViewModel
import com.moveagency.myterminal.presentation.bookmarks.details.model.BookmarkDetailsViewModelArgs
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun BookmarkDetailsScreen(
    navController: NavController,
    flightId: String,
    modifier: Modifier = Modifier,
    viewModel: BookmarkDetailsViewModel = koinViewModel {
        parametersOf(BookmarkDetailsViewModelArgs(flightId))
    },
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.navigation.HandleNavigation(navController)

    DetailsScreenContent(
        uiState = uiState,
        onBookmark = viewModel::onBookmark,
        onRetry = viewModel::onRetry,
        onBackPress = viewModel::onBackPressed,
        modifier = modifier,
    )
}
