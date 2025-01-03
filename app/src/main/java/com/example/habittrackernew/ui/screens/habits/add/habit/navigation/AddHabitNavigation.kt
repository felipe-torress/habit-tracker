package com.example.habittrackernew.ui.screens.habits.add.habit.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.habittrackernew.ui.screens.habits.add.habit.AddHabitRoute
import com.example.habittrackernew.ui.screens.habits.add.task.navigation.AddTask
import kotlinx.serialization.Serializable

fun NavGraphBuilder.addHabitScreen(navController: NavController) = composable<AddHabit> {
    AddHabitRoute(
        navigateBack = { navController.popBackStack() },
        onAddTaskClick = { navController.navigate(AddTask) }
    )
}

@Serializable
object AddHabit
