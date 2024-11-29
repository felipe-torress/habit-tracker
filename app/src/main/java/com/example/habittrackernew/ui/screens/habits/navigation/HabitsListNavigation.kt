package com.example.habittrackernew.ui.screens.habits.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.habittrackernew.ui.screens.habits.HabitsListRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.habitsListScreen(
    navController: NavController,
    navOptions: NavOptions,
) = composable<HabitList> {
    HabitsListRoute(onAddHabitClick = { })
}

@Serializable
object HabitList
