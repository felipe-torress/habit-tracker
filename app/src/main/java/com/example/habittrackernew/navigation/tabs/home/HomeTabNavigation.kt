package com.example.habittrackernew.navigation.tabs.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import com.example.habittrackernew.ui.screen.habits.navigation.HABITS_LIST_ROUTE

const val HOME_TAB_ROUTE = "home_tab_route"

fun NavController.navigateToHomeTab(navOptions: NavOptions) = navigate(HOME_TAB_ROUTE, navOptions)

fun NavGraphBuilder.homeNavGraph(navController: NavController) = navigation(
    startDestination = HABITS_LIST_ROUTE,
    route = HOME_TAB_ROUTE
) {
    // home screen
}