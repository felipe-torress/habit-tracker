package com.example.habittracker.navigation.tabs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import com.example.habittracker.ui.screens.progress.navigation.Progress
import com.example.habittracker.ui.screens.progress.navigation.progressScreen

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
