package com.example.habittrackernew.ui.screens.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.habittrackernew.ui.screens.profile.ProfileRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.profileScreen() =
    composable<Profile> {
        ProfileRoute()
    }

@Serializable
object Profile
