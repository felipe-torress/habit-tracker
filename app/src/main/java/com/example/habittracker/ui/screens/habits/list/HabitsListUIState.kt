package com.example.habittracker.ui.screens.habits.list

import com.example.habittracker.ui.screens.habits.model.HabitUIData

sealed interface HabitsListUIState {
    data object Loading : HabitsListUIState

    data object NoHabits : HabitsListUIState

    data class Success(val habits: List<HabitUIData>) : HabitsListUIState
}
