package com.example.habittracker.navigation.tabs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import com.example.habittracker.ui.screens.profile.navigation.Profile
import com.example.habittracker.ui.screens.profile.navigation.profileScreen

fun NavController.navigateToProfileTab(navOptions: NavOptions) =
    navigate(
        route = TopLevelRoutes.ProfileTab,
        navOptions = navOptions,
    )

fun NavGraphBuilder.profileNavGraph() =
    navigation<TopLevelRoutes.ProfileTab>(startDestination = Profile) {
        profileScreen()
    }
