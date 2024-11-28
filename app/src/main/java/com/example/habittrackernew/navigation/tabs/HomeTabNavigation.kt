package com.example.habittrackernew.navigation.tabs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import com.example.habittrackernew.ui.screens.home.navigation.HOME_ROUTE
import com.example.habittrackernew.ui.screens.home.navigation.homeScreen

const val HOME_TAB_ROUTE = "home_tab_route"

fun NavController.navigateToHomeTab(navOptions: NavOptions) = navigate(HOME_TAB_ROUTE, navOptions)

fun NavGraphBuilder.homeNavGraph() =
    navigation(
        startDestination = HOME_ROUTE,
        route = HOME_TAB_ROUTE,
    ) {
        homeScreen()
    }
