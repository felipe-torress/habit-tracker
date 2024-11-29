package com.example.habittrackernew.ui.screens.habits

import com.example.habittrackernew.ui.screens.habits.model.HabitUIData

interface HabitsListUIState {
    data object Loading : HabitsListUIState
    data class Success(val habits: List<HabitUIData>) : HabitsListUIState
}
