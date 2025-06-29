package com.moveagency.shared.myterminal.presentation.home.navigation

sealed interface HomeNavigation {

    data class OpenDetailPage(val id: String) : HomeNavigation
}
