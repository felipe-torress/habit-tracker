package com.example.habittrackernew.ui.screens.habits.add.habit

import com.example.data.model.HabitColor
import com.example.habittrackernew.ui.screens.habits.model.HabitTaskUIData

sealed interface AddHabitUIState {
    data object Loading : AddHabitUIState

    data class Success(
        val name: String,
        val color: HabitColor,
        val tasks: List<HabitTaskUIData>,
    ) : AddHabitUIState
}
