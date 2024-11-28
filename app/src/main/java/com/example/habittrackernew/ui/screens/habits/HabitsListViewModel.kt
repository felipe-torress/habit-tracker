package com.example.habittrackernew.ui.screens.habits

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HabitsListViewModel @Inject constructor() : ViewModel() {
    val uiState: StateFlow<HabitsListUIState> = MutableStateFlow(HabitsListUIState.Success)
}
