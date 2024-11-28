package com.example.habittrackernew.ui.screens.habits

interface HabitsListUIState {
    data object Loading : HabitsListUIState

    data object Success : HabitsListUIState
}
