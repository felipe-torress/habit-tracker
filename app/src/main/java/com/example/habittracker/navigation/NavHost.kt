package com.example.habittracker.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.habittracker.navigation.tabs.TopLevelRoutes
import com.example.habittracker.navigation.tabs.habitsNavGraph
import com.example.habittracker.navigation.tabs.homeNavGraph
import com.example.habittracker.navigation.tabs.profileNavGraph
import com.example.habittracker.navigation.tabs.progressNavGraph
import com.example.habittracker.ui.AppState

@Composable
fun HabitTrackerNavHost(
    modifier: Modifier = Modifier,
    appState: AppState,
    startDestination: TopLevelRoutes = TopLevelRoutes.HabitsTab,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { scaleIn(animationSpec = tween(300)) },
        exitTransition = { scaleOut(animationSpec = tween(300)) },
        modifier = modifier,
    ) {
        homeNavGraph()
        habitsNavGraph(navController)
        progressNavGraph()
        profileNavGraph()
    }
}
