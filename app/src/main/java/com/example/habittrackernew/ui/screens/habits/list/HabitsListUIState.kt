package com.example.habittrackernew.ui.screens.habits.list

import com.example.habittrackernew.ui.screens.habits.model.HabitUIData

sealed interface HabitsListUIState {
    data object Loading : HabitsListUIState
    data object NoHabits : HabitsListUIState
    data class Success(val habits: List<HabitUIData>) : HabitsListUIState
}
