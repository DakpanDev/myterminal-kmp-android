package com.moveagency.myterminal.navigation.generic.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import com.moveagency.myterminal.R
import com.moveagency.myterminal.navigation.MainNavGraph.Bookmarks
import com.moveagency.myterminal.navigation.MainNavGraph.Home
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.colors
import com.moveagency.myterminal.ui.theme.MyTerminalTheme.typography
import com.moveagency.myterminal.ui.theme.Spacing

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier,
) {
    val backstackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backstackEntry?.destination
    val navigateToScreen = { direction: Any ->
        if (currentDestination?.hasRoute(direction::class) != true) {
            navController.navigate(direction) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }

    BottomNavItem(
        text = R.string.navigation_home,
        inactiveIcon = R.drawable.ic_home,
        activeIcon = R.drawable.ic_home_active,
        isSelected = currentDestination?.hasRoute(Home::class) == true,
        modifier = Modifier
            .weight(1F)
            .clickable { navigateToScreen(Home) },
    )
    BottomNavItem(
        text = R.string.navigation_bookmarks,
        inactiveIcon = R.drawable.ic_bookmarks,
        activeIcon = R.drawable.ic_bookmarks_active,
        isSelected = currentDestination?.hasRoute(Bookmarks::class) == true,
        modifier = Modifier
            .weight(1F)
            .clickable { navigateToScreen(Bookmarks) },
    )
}

@Composable
private fun BottomNavItem(
    @StringRes text: Int,
    @DrawableRes inactiveIcon: Int,
    @DrawableRes activeIcon: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(Spacing.x0_5),
) {
    Icon(
        painter = painterResource(if (isSelected) activeIcon else inactiveIcon),
        contentDescription = null,
        tint = colors.white,
    )
    Text(
        text = stringResource(text),
        color = colors.white,
        style = typography.bodySmall,
    )
}
