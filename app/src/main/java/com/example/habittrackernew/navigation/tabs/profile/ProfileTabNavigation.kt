package com.example.habittrackernew.navigation.tabs.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import com.example.habittrackernew.ui.screen.habits.navigation.HABITS_LIST_ROUTE

const val PROFILE_TAB_ROUTE = "profile_tab_route"

fun NavController.navigateToProfileTab(navOptions: NavOptions) = navigate(PROFILE_TAB_ROUTE, navOptions)

fun NavGraphBuilder.profileNavGraph(navController: NavController) = navigation(
    startDestination = HABITS_LIST_ROUTE,
    route = PROFILE_TAB_ROUTE
) {
    // profile screen
}