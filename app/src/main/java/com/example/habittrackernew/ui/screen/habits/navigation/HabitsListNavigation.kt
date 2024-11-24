package com.example.habittrackernew.ui.screen.habits.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.habittracker.feature.habits.add.navigation.navigateToAddHabitScreen
import com.example.habittracker.feature.habits.list.HabitsListRoute

const val HABITS_LIST_ROUTE = "habits_list_route"

fun NavController.navigateToHabitsListScreen(navOptions: NavOptions) = navigate(HABITS_LIST_ROUTE, navOptions)

fun NavGraphBuilder.habitsListScreen(navController: NavController, navOptions: NavOptions) = composable(route = HABITS_LIST_ROUTE) {
    HabitsListRoute(onAddHabitClick = { navController.navigateToAddHabitScreen(navOptions) })
}
