package com.moveagency.myterminal.navigation

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Left
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Right
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.moveagency.myterminal.bookmarks.BookmarksScreen
import com.moveagency.myterminal.bookmarks.details.BookmarkDetailsScreen
import com.moveagency.myterminal.home.HomeScreen
import com.moveagency.myterminal.generic.details.FlightDetailsScreen
import com.moveagency.myterminal.navigation.MainNavGraph.BookmarkDetails
import com.moveagency.myterminal.navigation.MainNavGraph.Bookmarks
import com.moveagency.myterminal.navigation.MainNavGraph.FlightDetails
import com.moveagency.myterminal.navigation.MainNavGraph.Home
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.colors
import kotlinx.serialization.Serializable

object MainNavGraph {

    @Serializable
    data object Home

    @Serializable
    data object Bookmarks

    @Serializable
    data class FlightDetails(
        val flightId: String,
    )

    @Serializable
    data class BookmarkDetails(
        val flightId: String,
    )
}

fun NavGraphBuilder.mainRoutes(
    navController: NavController,
) {
    composable<Home> {
        HomeScreen(navController)
    }
    composable<Bookmarks> {
        BookmarksScreen(navController)
    }
    composable<FlightDetails>(
        enterTransition = { slideIntoContainer(towards = Left) },
        exitTransition = { slideOutOfContainer(towards = Right) },
    ) {
        val route = it.toRoute<FlightDetails>()
        FlightDetailsScreen(
            navController = navController,
            flightId = route.flightId,
            modifier = Modifier
                .statusBarsPadding()
                .background(colors.primaryGray),
        )
    }
    composable<BookmarkDetails>(
        enterTransition = { slideIntoContainer(towards = Left) },
        exitTransition = { slideOutOfContainer(towards = Right) },
    ) {
        val route = it.toRoute<BookmarkDetails>()
        BookmarkDetailsScreen(
            navController = navController,
            flightId = route.flightId,
            modifier = Modifier
                .statusBarsPadding()
                .background(colors.primaryGray),
        )
    }
}
