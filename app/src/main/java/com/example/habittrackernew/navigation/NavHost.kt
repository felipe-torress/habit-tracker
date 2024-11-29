package com.example.habittrackernew.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.habittrackernew.navigation.tabs.TopLevelRoutes
import com.example.habittrackernew.navigation.tabs.habitsNavGraph
import com.example.habittrackernew.navigation.tabs.homeNavGraph
import com.example.habittrackernew.navigation.tabs.profileNavGraph
import com.example.habittrackernew.navigation.tabs.progressNavGraph
import com.example.habittrackernew.ui.AppState

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
