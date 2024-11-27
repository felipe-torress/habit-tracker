package com.example.habittrackernew.ui.screen.habits

interface HabitsListUIState {
    data object Loading : HabitsListUIState

    data object Success : HabitsListUIState
}
