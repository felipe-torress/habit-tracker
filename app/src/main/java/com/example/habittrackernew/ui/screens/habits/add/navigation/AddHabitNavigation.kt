package com.example.habittrackernew.ui.screens.habits.add.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.habittrackernew.ui.screens.habits.add.AddHabitRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.addHabitScreen() = composable<AddHabit> {
    AddHabitRoute()
}

@Serializable
object AddHabit
