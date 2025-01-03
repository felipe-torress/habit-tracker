package com.example.habittrackernew.navigation.tabs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import com.example.habittrackernew.ui.screens.progress.navigation.Progress
import com.example.habittrackernew.ui.screens.progress.navigation.progressScreen

fun NavController.navigateToProgressTab(navOptions: NavOptions) =
    navigate(
        TopLevelRoutes.ProgressTab,
        navOptions,
    )

fun NavGraphBuilder.progressNavGraph() =
    navigation<TopLevelRoutes.ProgressTab>(
        startDestination = Progress,
    ) {
        progressScreen()
    }
