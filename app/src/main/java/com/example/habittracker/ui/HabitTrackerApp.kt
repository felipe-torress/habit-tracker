package com.example.habittracker.ui

import android.app.Activity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.habittracker.navigation.HabitTrackerNavHost
import com.example.habittracker.navigation.TopLevelDestination
import com.example.habittracker.ui.composables.bottombar.HabitTrackerBottomBar
import com.example.habittracker.ui.theme.HabitTrackerColors

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HabitTrackerApp(
    appState: AppState = rememberAppState(),
    statusBarColor: Color,
) {
    // Set the status bar text color to dark
    val window = (LocalContext.current as Activity).window
    val controller = WindowInsetsControllerCompat(window, window.decorView)
    controller.isAppearanceLightStatusBars = true
    controller.isAppearanceLightNavigationBars = true

    // Animated Status Bar Color that smoothly transitions between values
    val animatedStatusBarColor by animateColorAsState(
        targetValue = statusBarColor,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing,
        ),
        label = "statusBarColor",
    )

    Scaffold(
        modifier = Modifier.semantics { testTagsAsResourceId = true },
        containerColor = HabitTrackerColors.backgroundColor,
        bottomBar = {
            HabitTrackerBottomBar(
                destinations = appState.topLevelDestinations,
                currentDestination = appState.currentDestination,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                modifier = Modifier.testTag("HabitTrackerBottomBar"),
            )
        },
    ) { paddingValues ->
        HabitTrackerNavHost(
            appState = appState,
            modifier = Modifier
                .background(animatedStatusBarColor)
                .fillMaxSize()
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues),
        )
    }
}

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
