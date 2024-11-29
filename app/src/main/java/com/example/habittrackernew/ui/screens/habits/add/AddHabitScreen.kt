package com.example.habittrackernew.ui.screens.habits.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AddHabitRoute(
    viewModel: AddHabitViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    AddHabitScreen(uiState)
}

@Composable
fun AddHabitScreen(
    uiState: AddHabitUIState
) {

}
