package com.example.habittrackernew.navigation.tabs.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import com.example.habittrackernew.ui.screen.profile.navigation.PROFILE_ROUTE
import com.example.habittrackernew.ui.screen.profile.navigation.profileScreen

const val PROFILE_TAB_ROUTE = "profile_tab_route"

fun NavController.navigateToProfileTab(navOptions: NavOptions) =
    navigate(
        PROFILE_TAB_ROUTE,
        navOptions,
    )

fun NavGraphBuilder.profileNavGraph() =
    navigation(
        startDestination = PROFILE_ROUTE,
        route = PROFILE_TAB_ROUTE,
    ) {
        profileScreen()
    }
