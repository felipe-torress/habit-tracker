package com.example.habittracker.ui.screens.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.habittracker.ui.screens.home.HomeRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.homeScreen() =
    composable<Home> {
        HomeRoute()
    }

@Serializable
object Home
