package com.example.habittrackernew.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.habittrackernew.navigation.tabs.habits.HABITS_TAB_ROUTE
import com.example.habittrackernew.navigation.tabs.habits.habitsNavGraph
import com.example.habittrackernew.navigation.tabs.home.homeNavGraph
import com.example.habittrackernew.navigation.tabs.profile.profileNavGraph
import com.example.habittrackernew.navigation.tabs.progress.progressNavGraph
import com.example.habittrackernew.ui.AppState

@Composable
fun HabitTrackerNavHost(
    modifier: Modifier = Modifier,
    appState: AppState,
    startDestination: String = HABITS_TAB_ROUTE,
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
