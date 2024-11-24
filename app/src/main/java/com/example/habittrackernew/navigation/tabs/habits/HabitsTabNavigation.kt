package com.example.habittrackernew.navigation.tabs.habits

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import androidx.navigation.navOptions
import com.example.habittrackernew.ui.screen.habits.navigation.HABITS_LIST_ROUTE

const val HABITS_TAB_ROUTE = "habits_tab_route"

fun NavController.navigateToHabitsTab(navOptions: NavOptions) = navigate(HABITS_TAB_ROUTE, navOptions)

fun NavGraphBuilder.habitsNavGraph(navController: NavController) = navigation(
    startDestination = HABITS_LIST_ROUTE,
    route = HABITS_TAB_ROUTE
) {
    val navOptions = navOptions {
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

    habitsListScreen(navController, navOptions)
    addHabitScreen(navController)
}