package com.example.habittrackernew.ui.screens.habits.details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.habittrackernew.ui.screens.habits.add.task.navigation.AddTask
import com.example.habittrackernew.ui.screens.habits.details.HabitDetailsRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.habitDetailsScreen(navController: NavController) =
    composable<HabitDetails> { backStackEntry ->
        val habitDetailsRoute: HabitDetails = backStackEntry.toRoute()
        HabitDetailsRoute(
            habitId = habitDetailsRoute.habitId,
            navigateBack = { navController.popBackStack() },
            navigateToEditTask = { taskId -> navController.navigate(AddTask(taskId)) },
        )
    }

@Serializable
data class HabitDetails(val habitId: String)
