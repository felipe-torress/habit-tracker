package com.example.habittracker.ui.screens.habits.add.task.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.habittracker.navigation.CustomNavType
import com.example.habittracker.ui.screens.habits.add.task.TaskEntryFlow
import com.example.habittracker.ui.screens.habits.add.task.TaskEntryRoute
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

fun NavGraphBuilder.taskEntryScreen(navController: NavController) =
    composable<TaskEntry>(
        typeMap = mapOf(
            typeOf<TaskEntryFlow>() to CustomNavType.TaskEntryFlowType,
        ),
    ) { backStackEntry ->
        val taskEntryRoute: TaskEntry = backStackEntry.toRoute()
        TaskEntryRoute(
            taskEntryFlow = taskEntryRoute.taskEntryFlow,
            navigateBack = { navController.popBackStack() },
        )
    }

@Serializable
data class TaskEntry(val taskEntryFlow: TaskEntryFlow)
