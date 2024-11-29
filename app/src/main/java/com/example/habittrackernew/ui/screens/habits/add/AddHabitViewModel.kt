package com.example.habittrackernew.ui.screens.habits.add

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddHabitViewModel @Inject constructor() : ViewModel() {
    val uiState: StateFlow<AddHabitUIState> = MutableStateFlow(AddHabitUIState.Loading)
}
