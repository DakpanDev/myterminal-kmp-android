package com.moveagency.myterminal.presentation.home.details.navigation

sealed interface DetailsNavigation {

    data object GoBack : DetailsNavigation
}
