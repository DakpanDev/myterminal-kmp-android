package com.moveagency.myterminal.bookmarks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.m2mobi.utility.poser.extension.RetrieveAsEffect
import com.m2mobi.utility.utils.android.mvvm.EventFlow
import com.moveagency.myterminal.navigation.MainNavGraph.BookmarkDetails
import com.moveagency.myterminal.presentation.bookmarks.model.BookmarksNavigation
import com.moveagency.myterminal.presentation.bookmarks.model.BookmarksNavigation.OpenDetails

@Composable
fun EventFlow<BookmarksNavigation>.HandleNavigation(navController: NavController) {
    RetrieveAsEffect {
        when (it) {
            is OpenDetails -> navController.navigate(BookmarkDetails(it.flightId))
        }
    }
}
