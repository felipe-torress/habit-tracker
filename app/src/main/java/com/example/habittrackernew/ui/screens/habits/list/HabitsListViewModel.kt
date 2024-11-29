package com.example.habittrackernew.ui.screens.habits.list

import androidx.lifecycle.ViewModel
import com.example.habittrackernew.ui.utils.previews.Mocks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HabitsListViewModel @Inject constructor() : ViewModel() {
    val uiState: StateFlow<HabitsListUIState> = MutableStateFlow(HabitsListUIState.Success(listOf(Mocks.habitUIData_1)))
}
