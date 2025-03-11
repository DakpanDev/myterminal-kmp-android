package com.moveagency.myterminal.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.m2mobi.utility.poser.extension.RetrieveAsEffect
import com.m2mobi.utility.utils.android.mvvm.EventFlow
import com.moveagency.myterminal.navigation.MainNavGraph.FlightDetails
import com.moveagency.myterminal.presentation.home.navigation.HomeNavigation

@Composable
fun EventFlow<HomeNavigation>.HandleNavigation(navController: NavController) {
    RetrieveAsEffect {
        when (it) {
            is HomeNavigation.OpenDetailPage -> navController.navigate(FlightDetails(it.id))
        }
    }
}
