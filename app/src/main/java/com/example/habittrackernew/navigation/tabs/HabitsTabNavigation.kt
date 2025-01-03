package com.example.habittrackernew.navigation.tabs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import androidx.navigation.navOptions
import com.example.habittrackernew.ui.screens.habits.add.habit.navigation.addHabitScreen
import com.example.habittrackernew.ui.screens.habits.add.task.navigation.addTaskScreen
import com.example.habittrackernew.ui.screens.habits.list.navigation.HabitList
import com.example.habittrackernew.ui.screens.habits.list.navigation.habitsListScreen

fun NavController.navigateToHabitsTab(navOptions: NavOptions) =
    navigate(
        route = TopLevelRoutes.HabitsTab,
        navOptions = navOptions,
    )

fun NavGraphBuilder.habitsNavGraph(navController: NavController) =
    navigation<TopLevelRoutes.HabitsTab>(startDestination = HabitList) {
        val navOptions =
            navOptions {
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

        habitsListScreen(navController, navOptions)
        addHabitScreen(navController)
        addTaskScreen(navController)
    }
