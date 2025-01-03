package com.example.habittrackernew.ui.screen.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.habittrackernew.ui.screen.profile.ProfileRoute

const val PROFILE_ROUTE = "profile_route"

fun NavGraphBuilder.profileScreen() =
    composable(route = PROFILE_ROUTE) {
        ProfileRoute()
    }
