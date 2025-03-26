package com.moveagency.shared.myterminal.generic.details.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.m2mobi.utility.poser.extension.RetrieveAsEffect
import com.m2mobi.utility.utils.android.mvvm.EventFlow
import com.moveagency.shared.myterminal.presentation.home.details.navigation.DetailsNavigation

@Composable
fun EventFlow<DetailsNavigation>.HandleNavigation(
    navController: NavController,
) {
    RetrieveAsEffect {
        when (it) {
            DetailsNavigation.GoBack -> navController.popBackStack()
        }
    }
}
