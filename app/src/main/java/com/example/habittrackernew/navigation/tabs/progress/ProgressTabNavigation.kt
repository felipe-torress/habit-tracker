package com.example.habittrackernew.navigation.tabs.progress

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import com.example.habittrackernew.ui.screens.progress.navigation.PROGRESS_ROUTE
import com.example.habittrackernew.ui.screens.progress.navigation.progressScreen

const val PROGRESS_TAB_ROUTE = "progress_tab_route"

fun NavController.navigateToProgressTab(navOptions: NavOptions) =
    navigate(
        PROGRESS_TAB_ROUTE,
        navOptions,
    )

fun NavGraphBuilder.progressNavGraph() =
    navigation(
        startDestination = PROGRESS_ROUTE,
        route = PROGRESS_TAB_ROUTE,
    ) {
        progressScreen()
    }
