package com.example.habittracker.ui.screens.habits.add.task.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.habittracker.ui.screens.habits.add.task.TaskEntryRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.taskEntryScreen(navController: NavController) =
    composable<TaskEntry> { backStackEntry ->
        val taskEntryRoute: TaskEntry = backStackEntry.toRoute()
        TaskEntryRoute(
            taskId = taskEntryRoute.taskId,
            navigateBack = { navController.popBackStack() },
        )
    }

@Serializable
data class TaskEntry(val taskId: String? = null)
