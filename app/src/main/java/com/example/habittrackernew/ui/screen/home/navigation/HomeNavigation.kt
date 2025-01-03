package com.example.habittrackernew.ui.screen.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.habittrackernew.ui.screen.home.HomeRoute

const val HOME_ROUTE = "home_route"

fun NavGraphBuilder.homeScreen() =
    composable(route = HOME_ROUTE) {
        HomeRoute()
    }
