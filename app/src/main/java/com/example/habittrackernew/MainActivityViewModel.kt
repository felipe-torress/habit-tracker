package com.example.habittrackernew

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {
    private val _statusBarColor = MutableStateFlow(HabitTrackerColors.backgroundColor)
    val statusBarColor: StateFlow<Color> get() = _statusBarColor.asStateFlow()

    fun updateStatusBarColor(color: Color) = _statusBarColor.update { color }

    fun resetStatusBarColor() = _statusBarColor.update { HabitTrackerColors.backgroundColor }
}
