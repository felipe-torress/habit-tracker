package com.example.habittrackernew.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.habittrackernew.navigation.TopLevelDestination
import com.example.habittrackernew.navigation.tabs.navigateToHabitsTab
import com.example.habittrackernew.navigation.tabs.navigateToHomeTab
import com.example.habittrackernew.navigation.tabs.navigateToProfileTab
import com.example.habittrackernew.navigation.tabs.navigateToProgressTab

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()): AppState =
    remember(navController) { AppState(navController) }

@Stable
class AppState(val navController: NavHostController) {
    val currentDestination: NavDestination?
        @Composable get() =
            navController
                .currentBackStackEntryAsState().value?.destination

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions =
            navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigateToHomeTab(topLevelNavOptions)
            TopLevelDestination.HABITS -> navController.navigateToHabitsTab(topLevelNavOptions)
            TopLevelDestination.PROGRESS -> navController.navigateToProgressTab(topLevelNavOptions)
            TopLevelDestination.PROFILE -> navController.navigateToProfileTab(topLevelNavOptions)
        }
    }
}
