package com.example.habittracker.ui.screens.habits.add.habit.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.habittracker.ui.screens.habits.add.habit.AddHabitRoute
import com.example.habittracker.ui.screens.habits.add.task.navigation.TaskEntry
import kotlinx.serialization.Serializable

fun NavGraphBuilder.addHabitScreen(navController: NavController) =
    composable<AddHabit> {
        AddHabitRoute(
            navigateBack = { navController.popBackStack() },
            navigateToTaskEntry = { taskEntryFlow -> navController.navigate(TaskEntry(taskEntryFlow)) },
        )
    }

@Serializable
object AddHabit
