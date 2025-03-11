package com.moveagency.myterminal.bookmarks

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.moveagency.myterminal.R
import com.moveagency.myterminal.bookmarks.navigation.HandleNavigation
import com.moveagency.myterminal.generic.composable.FlightListItem
import com.moveagency.myterminal.generic.composable.TitleTopBar
import com.moveagency.myterminal.presentation.bookmarks.BookmarksViewModel
import com.moveagency.myterminal.presentation.bookmarks.model.BookmarksUIModel
import com.moveagency.myterminal.ui.theme.MyTerminalTheme
import com.moveagency.myterminal.ui.theme.Spacing
import kotlinx.collections.immutable.persistentListOf
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookmarksScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: BookmarksViewModel = koinViewModel(),
) {
    val uiModel by viewModel.uiModel.collectAsStateWithLifecycle()

    viewModel.navigation.HandleNavigation(navController)

    BookmarksContent(
        uiModel = uiModel,
        onFlightClick = viewModel::onFlightClicked,
        modifier = modifier,
    )
}

@Composable
private fun BookmarksContent(
    uiModel: BookmarksUIModel,
    onFlightClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier) {
    val scrollState = rememberScrollState()
    val topbarShadow by remember(scrollState) {
        derivedStateOf {
            if (scrollState.value > 0) Modifier.shadow(elevation = Spacing.x2) else Modifier
        }
    }

    TitleTopBar(
        title = stringResource(R.string.bookmarks_title),
        modifier = Modifier
            .fillMaxWidth()
            .then(topbarShadow)
    )
    Column(
        modifier = Modifier
            .padding(horizontal = Spacing.x3)
            .padding(bottom = Spacing.x2)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(Spacing.x3),
    ) {
        for (flight in uiModel.flights) {
            FlightListItem(
                uiModel = flight,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onFlightClick(flight.id) },
            )
        }
    }
}

@Composable
@Preview
private fun PreviewBookmarksContent() = MyTerminalTheme {
    val uiModel = BookmarksUIModel(
        flights = persistentListOf(),
    )

    BookmarksContent(
        uiModel = uiModel,
        onFlightClick = {},
    )
}
