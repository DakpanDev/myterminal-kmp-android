package com.moveagency.myterminal.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.moveagency.myterminal.navigation.MainNavGraph.Home
import com.moveagency.myterminal.navigation.generic.composable.BottomNavBar
import com.moveagency.myterminal.navigation.mainRoutes
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.colors

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.primaryGray),
    ) {
        NavHost(
            navController = navController,
            startDestination = Home,
            modifier = Modifier.weight(1F),
        ) {
            mainRoutes(navController)
        }
        BottomNavBar(
            navController = navController,
            modifier = Modifier.navigationBarsPadding(),
        )
    }
}
