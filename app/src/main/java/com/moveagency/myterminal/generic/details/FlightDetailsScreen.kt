package com.moveagency.myterminal.generic.details

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.m2mobi.utility.utils.android.mvvm.model.TypedUIState
import com.moveagency.myterminal.R
import com.moveagency.myterminal.generic.composable.state.ErrorState
import com.moveagency.myterminal.generic.composable.state.LoadingState
import com.moveagency.myterminal.generic.details.composable.*
import com.moveagency.myterminal.generic.extension.toDisplayString
import com.moveagency.myterminal.generic.details.navigation.HandleNavigation
import com.moveagency.myterminal.presentation.generic.model.*
import com.moveagency.myterminal.presentation.home.details.DetailsViewModel
import com.moveagency.myterminal.ui.theme.MyTerminalTheme
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.colors
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.typography
import com.moveagency.myterminal.ui.theme.Spacing
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun FlightDetailsScreen(
    navController: NavController,
    flightId: String,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = koinViewModel { parametersOf(DetailsViewModelArgs(flightId)) }
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.navigation.HandleNavigation(navController)

    DetailsScreenContent(
        uiState = uiState,
        onBookmark = viewModel::onBookmark,
        onRetry = viewModel::onRetry,
        onBackPress = viewModel::onBackPressed,
        modifier = modifier.verticalScroll(rememberScrollState()),
    )
}

@Composable
fun DetailsScreenContent(
    uiState: TypedUIState<DetailsUIModel, Unit>,
    onBookmark: () -> Unit,
    onRetry: () -> Unit,
    onBackPress: () -> Unit,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
) {
    Row(
        modifier = Modifier
            .padding(top = Spacing.x2)
            .padding(horizontal = Spacing.x3)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_arrow_back),
            contentDescription = null,
            modifier = Modifier.clickable(onClick = onBackPress),
            tint = colors.white,
        )

        if (uiState is TypedUIState.Normal) {
            Icon(
                painter = painterResource(
                    if (uiState.data.isBookmarked) {
                        R.drawable.ic_bookmark_filled
                    } else {
                        R.drawable.ic_bookmark
                    }
                ),
                contentDescription = null,
                modifier = Modifier.clickable(onClick = onBookmark),
                tint = colors.white,
            )
        }
    }

    when (uiState) {
        is TypedUIState.Error -> ErrorState(
            text = stringResource(R.string.retrieve_details_error),
            onRetry = onRetry,
            modifier = Modifier.fillMaxWidth(),
        )
        is TypedUIState.Loading -> LoadingState(
            modifier = Modifier.fillMaxSize(),
        )
        is TypedUIState.Normal -> NormalContent(
            uiModel = uiState.data,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun NormalContent(
    uiModel: DetailsUIModel,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier) {
    val lastUpdatedDate = remember { uiModel.lastUpdated.date.toDisplayString() }
    val lastUpdatedTime = remember { uiModel.lastUpdated.time.toDisplayString() }

    GeneralDetails(
        flightName = uiModel.name,
        destination = uiModel.destination,
        states = uiModel.states,
        departureDateTime = uiModel.departureDateTime,
        modifier = Modifier
            .padding(horizontal = Spacing.x3)
            .padding(top = Spacing.x1, bottom = Spacing.x3),
    )
    LocationDetails(
        terminal = uiModel.terminal,
        checkInRows = uiModel.checkinRows,
        gate = uiModel.gate,
        modifier = Modifier.padding(bottom = Spacing.x3),
    )
    TimeDetails(
        checkInClose = uiModel.checkinClosingTime,
        gateOpening = uiModel.gateOpeningTime,
        boardingTime = uiModel.boardingTime,
        actualDeparture = uiModel.actualDepartureTime,
        modifier = Modifier
            .padding(horizontal = Spacing.x3)
            .padding(bottom = Spacing.x3),
    )
    HorizontalDivider()
    Text(
        text = stringResource(R.string.details_last_updated, lastUpdatedDate, lastUpdatedTime),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Spacing.x2),
        color = colors.white,
        textAlign = TextAlign.Center,
        style = typography.bodySmall,
    )
}

@Composable
@Preview
private fun PreviewDetailsScreen() = MyTerminalTheme {
    val uiState = TypedUIState.Normal(
        data = DetailsUIModel(
            id = "flight_id",
            name = "HV6935",
            destination = "TIA",
            states = persistentListOf(FlightStatus.Departed),
            departureDateTime = LocalDateTime(2025, 2, 10, 7, 45),
            terminal = 1,
            checkinRows = persistentListOf("1", "2", "3"),
            gate = "E18",
            checkinClosingTime = LocalTime(7, 5),
            gateOpeningTime = LocalTime(6, 45),
            boardingTime = LocalTime(7, 15),
            actualDepartureTime = LocalTime(7, 43),
            lastUpdated = LocalDateTime(2025, 2, 10, 7, 59, 4),
            isBookmarked = false,
        )
    )

    DetailsScreenContent(
        uiState = uiState,
        onBookmark = {},
        onRetry = {},
        onBackPress = {},
        modifier = Modifier.fillMaxWidth(),
    )
}
