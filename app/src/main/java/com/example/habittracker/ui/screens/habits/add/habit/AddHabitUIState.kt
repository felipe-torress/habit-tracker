package com.example.habittracker.ui.screens.habits.add.habit

import com.example.data.model.HabitColor
import com.example.habittracker.ui.screens.habits.model.HabitTaskUIData

sealed interface AddHabitUIState {
    data object Loading : AddHabitUIState

    data class Success(
        val name: String,
        val color: HabitColor,
        val tasks: List<HabitTaskUIData>,
    ) : AddHabitUIState
}
