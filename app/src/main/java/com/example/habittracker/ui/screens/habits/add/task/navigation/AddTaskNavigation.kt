package com.example.habittracker.ui.screens.habits.add.task.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.habittracker.ui.screens.habits.add.task.AddTaskRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.addTaskScreen(navController: NavController) =
    composable<AddTask> { backStackEntry ->
        val addTaskRoute: AddTask = backStackEntry.toRoute()
        AddTaskRoute(
            taskId = addTaskRoute.taskId,
            navigateBack = { navController.popBackStack() },
        )
    }

@Serializable
data class AddTask(val taskId: String? = null)
