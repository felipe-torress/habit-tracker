package com.example.habittracker.navigation.tabs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import com.example.habittracker.ui.screens.home.navigation.Home
import com.example.habittracker.ui.screens.home.navigation.homeScreen

fun NavController.navigateToHomeTab(navOptions: NavOptions) = navigate(TopLevelRoutes.HomeTab, navOptions)

fun NavGraphBuilder.homeNavGraph() =
    navigation<TopLevelRoutes.HomeTab>(startDestination = Home) {
        homeScreen()
    }
