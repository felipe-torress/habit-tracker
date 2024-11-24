package com.example.habittrackernew.navigation.tabs.progress

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import com.example.habittrackernew.ui.screen.habits.navigation.HABITS_LIST_ROUTE

const val PROGRESS_TAB_ROUTE = "progress_tab_route"

fun NavController.navigateToProgressTab(navOptions: NavOptions) = navigate(PROGRESS_TAB_ROUTE, navOptions)

fun NavGraphBuilder.progressNavGraph(navController: NavController) = navigation(
    startDestination = HABITS_LIST_ROUTE,
    route = PROGRESS_TAB_ROUTE
) {
    // progress screen
}