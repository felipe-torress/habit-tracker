package com.example.habittrackernew.ui.screens.habits.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.habittrackernew.ui.screens.habits.HabitsListRoute

const val HABITS_LIST_ROUTE = "habits_list_route"

fun NavGraphBuilder.habitsListScreen(
    navController: NavController,
    navOptions: NavOptions,
) = composable(route = HABITS_LIST_ROUTE) {
    HabitsListRoute(onAddHabitClick = { })
}
