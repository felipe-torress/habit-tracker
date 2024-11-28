package com.example.habittrackernew.ui.screens.progress.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.habittrackernew.ui.screens.progress.ProgressRoute

const val PROGRESS_ROUTE = "progress_route"

fun NavGraphBuilder.progressScreen() =
    composable(route = PROGRESS_ROUTE) {
        ProgressRoute()
    }
