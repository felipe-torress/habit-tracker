package com.example.habittrackernew.ui.screens.habits.list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.habittrackernew.ui.screens.habits.add.habit.navigation.AddHabit
import com.example.habittrackernew.ui.screens.habits.details.navigation.HabitDetails
import com.example.habittrackernew.ui.screens.habits.list.HabitsListRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.habitsListScreen(
    navController: NavController,
    navOptions: NavOptions,
) = composable<HabitList> {
    HabitsListRoute(
        navigateToAddHabit = { navController.navigate(route = AddHabit, navOptions = navOptions) },
        navigateToHabitDetails = { habitId ->
            println("[Felipe] navigating to habit details with id: $habitId")
            navController.navigate(route = HabitDetails(habitId))
        }
    )
}

@Serializable
object HabitList
