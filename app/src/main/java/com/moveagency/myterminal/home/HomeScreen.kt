package com.moveagency.myterminal.home

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.m2mobi.utility.utils.android.mvvm.model.TypedUIState
import com.moveagency.myterminal.R
import com.moveagency.myterminal.generic.composable.*
import com.moveagency.myterminal.generic.composable.state.ErrorState
import com.moveagency.myterminal.generic.composable.state.LoadingState
import com.moveagency.myterminal.home.navigation.HandleNavigation
import com.moveagency.myterminal.presentation.generic.model.FlightUIModel
import com.moveagency.myterminal.presentation.home.HomeViewModel
import com.moveagency.myterminal.presentation.home.model.FlightListUIModel
import com.moveagency.myterminal.ui.theme.MyTerminalTheme
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.colors
import com.moveagency.myterminal.ui.theme.Spacing
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.navigation.HandleNavigation(navController)

    HomeScreenContent(
        uiState = uiState,
        onDateChange = viewModel::onDateChanged,
        onQueryChange = viewModel::onSearch,
        onRetry = viewModel::onRetry,
        onFlightClick = viewModel::onFlightClicked,
        onLoadMore = viewModel::onLoadMore,
        modifier = modifier,
    )
}

@Composable
private fun HomeScreenContent(
    uiState: TypedUIState<FlightListUIModel, Unit>,
    onDateChange: (LocalDate) -> Unit,
    onQueryChange: (String) -> Unit,
    onRetry: () -> Unit,
    onFlightClick: (String) -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier) {
    val scrollState = rememberScrollState()
    val topbarShadow by remember(scrollState) {
        derivedStateOf {
            if (scrollState.value > 0) Modifier.shadow(elevation = Spacing.x2) else Modifier
        }
    }
    val showLoadMore = uiState.normalDataOrNull()?.newFlightsLoading == true

    LaunchedEffect(scrollState.value) {
        if (scrollState.value == scrollState.maxValue) {
            onLoadMore()
        }
    }

    TitleTopBar(
        title = stringResource(R.string.home_title),
        modifier = Modifier
            .fillMaxWidth()
            .then(topbarShadow),
        onDateChange = onDateChange,
    )
    Column(
        modifier = Modifier
            .padding(horizontal = Spacing.x3)
            .verticalScroll(scrollState),
    ) {
        SearchBar(
            value = uiState.normalDataOrNull()?.searchQuery ?: "",
            onValueChange = onQueryChange,
            modifier = Modifier
                .padding(vertical = Spacing.x2)
                .fillMaxWidth(),
            placeholder = stringResource(R.string.search_placeholder),
        )
        when (uiState) {
            is TypedUIState.Error -> ErrorState(
                text = stringResource(R.string.retrieve_departures_error),
                onRetry = onRetry,
            )
            is TypedUIState.Loading -> LoadingState(
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            is TypedUIState.Normal -> FlightList(
                flights = uiState.data.flights,
                onFlightClick = onFlightClick,
                modifier = Modifier.padding(bottom = Spacing.x2),
            )
        }
        AnimatedVisibility(
            visible = showLoadMore,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut(),
        ) {
            var loadingHeight by remember { mutableIntStateOf(0) }

            LaunchedEffect(showLoadMore) {
                if (showLoadMore) scrollState.animateScrollBy(loadingHeight.toFloat())
            }

            LoadingState(
                modifier = Modifier
                    .fillMaxWidth()
                    .onSizeChanged { loadingHeight = it.height },
            )
        }
    }
}

@Composable
private fun FlightList(
    flights: ImmutableList<FlightUIModel>,
    onFlightClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(Spacing.x3),
) {
    for (flight in flights) {
        FlightListItem(
            uiModel = flight,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onFlightClick(flight.id) },
        )
    }
}

@Composable
@Preview
private fun PreviewNormalHomeScreen() = MyTerminalTheme {
    val today = LocalDate.now()
    val uiState = TypedUIState.Normal(
        data = FlightListUIModel(
            flights = persistentListOf(
                FlightUIModel(id = "1", name = "HV6935", destination = "TIA", date = today),
                FlightUIModel(id = "2", name = "HV5685", destination = "ACE", date = today),
                FlightUIModel(id = "3", name = "HV6901", destination = "DXB", date = today),
            )
        )
    )

    HomeScreenContent(
        uiState = uiState,
        onDateChange = {},
        onQueryChange = {},
        onRetry = {},
        onFlightClick = {},
        onLoadMore = {},
        modifier = Modifier.background(colors.primaryGray),
    )
}

@Composable
@Preview
private fun PreviewErrorHomeScreen() = MyTerminalTheme {
    val uiState = TypedUIState.Error(Unit)

    HomeScreenContent(
        uiState = uiState,
        onDateChange = {},
        onQueryChange = {},
        onRetry = {},
        onFlightClick = {},
        onLoadMore = {},
        modifier = Modifier.background(colors.primaryGray),
    )
}
