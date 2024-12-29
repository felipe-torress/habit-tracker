package com.example.habittrackernew.ui.screens.habits.add.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.habittrackernew.ui.screens.habits.add.AddHabitRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.addHabitScreen(navController: NavController) = composable<AddHabit> {
    AddHabitRoute(
        navigateBack = { navController.popBackStack() },
        onAddTaskClick = { } // TODO: Add navigation to Add Task Screen
    )
}

@Serializable
object AddHabit
