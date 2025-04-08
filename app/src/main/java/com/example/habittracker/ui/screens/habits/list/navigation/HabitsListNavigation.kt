package com.example.habittracker.ui.screens.habits.list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.habittracker.ui.screens.habits.add.habit.navigation.AddHabit
import com.example.habittracker.ui.screens.habits.add.task.navigation.TaskEntry
import com.example.habittracker.ui.screens.habits.details.navigation.HabitDetails
import com.example.habittracker.ui.screens.habits.list.HabitsListRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.habitsListScreen(
    navController: NavController,
    navOptions: NavOptions,
) = composable<HabitList> {
    HabitsListRoute(
        navigateToAddHabit = { navController.navigate(route = AddHabit, navOptions = navOptions) },
        navigateToHabitDetails = { habitId ->
            navController.navigate(route = HabitDetails(habitId))
        },
        navigateToTaskEntry = { taskEntryFlow ->
            navController.navigate(TaskEntry(taskEntryFlow))
        },
    )
}

@Serializable
object HabitList
