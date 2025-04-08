package com.example.habittracker.ui.screens.habits.details

import com.example.habittracker.ui.screens.habits.model.HabitUIData

sealed interface HabitDetailsUIState {
    data object Loading : HabitDetailsUIState

    data object Failure : HabitDetailsUIState

    data class Success(val habit: HabitUIData) : HabitDetailsUIState
}
