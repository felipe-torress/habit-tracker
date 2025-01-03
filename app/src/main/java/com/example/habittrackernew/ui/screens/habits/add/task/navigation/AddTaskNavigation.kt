package com.example.habittrackernew.ui.screens.habits.add.task.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.habittrackernew.ui.screens.habits.add.task.AddTaskRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.addTaskScreen(navController: NavController) =
    composable<AddTask> {
        AddTaskRoute(
            navigateBack = { navController.popBackStack() },
        )
    }

@Serializable
object AddTask
