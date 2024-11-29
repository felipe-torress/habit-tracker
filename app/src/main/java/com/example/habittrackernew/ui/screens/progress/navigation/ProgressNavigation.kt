package com.example.habittrackernew.ui.screens.progress.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.habittrackernew.ui.screens.progress.ProgressRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.progressScreen() =
    composable<Progress> {
        ProgressRoute()
    }

@Serializable
object Progress
