package com.example.habittracker

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.ui.theme.HabitTrackerColors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {
    private val _statusBarColor = MutableStateFlow(HabitTrackerColors.backgroundColor)
    private val _shouldHideSplashScreen = MutableStateFlow(false)
    val statusBarColor: StateFlow<Color> get() = _statusBarColor.asStateFlow()
    val shouldHideSplashScreen: StateFlow<Boolean> get() = _shouldHideSplashScreen.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000L)
            _shouldHideSplashScreen.update { true }
        }
    }

    fun updateStatusBarColor(color: Color) = _statusBarColor.update { color }

    fun resetStatusBarColor() = _statusBarColor.update { HabitTrackerColors.backgroundColor }
}
