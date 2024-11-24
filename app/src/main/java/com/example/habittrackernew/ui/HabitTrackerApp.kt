package com.example.habittrackernew.ui

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.habittrackernew.navigation.HabitTrackerNavHost
import com.example.habittrackernew.navigation.TopLevelDestination
import com.example.habittrackernew.ui.composables.bottombar.HabitTrackerBottomBar
import com.example.habittrackernew.ui.theme.HabitTrackerColors

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HabitTrackerApp(appState: AppState = rememberAppState()) {
    Scaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        containerColor = HabitTrackerColors.backgroundColor,
        bottomBar = {
            HabitTrackerBottomBar(
                destinations = appState.topLevelDestinations,
                currentDestination = appState.currentDestination,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                modifier = Modifier.testTag("HabitTrackerBottomBar")
            )
        }
    ) { paddingValues ->
        HabitTrackerNavHost(
            appState = appState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)
        )
    }
}

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false