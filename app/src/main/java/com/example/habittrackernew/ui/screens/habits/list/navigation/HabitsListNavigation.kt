package com.example.habittrackernew.ui.screens.habits.list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.habittrackernew.ui.screens.habits.add.navigation.AddHabit
import com.example.habittrackernew.ui.screens.habits.list.HabitsListRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.habitsListScreen(
    navController: NavController,
    navOptions: NavOptions,
) = composable<HabitList> {
    HabitsListRoute(onAddHabitClick = { navController.navigate(route = AddHabit, navOptions = navOptions) })
}

@Serializable
object HabitList
