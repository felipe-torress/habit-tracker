package com.example.habittrackernew.ui.screens.habits.details

import com.example.habittrackernew.ui.screens.habits.model.HabitUIData

sealed interface HabitDetailsUIState {
    data object Loading : HabitDetailsUIState
    data object Failure : HabitDetailsUIState
    data class Success(val habit: HabitUIData) : HabitDetailsUIState
}
