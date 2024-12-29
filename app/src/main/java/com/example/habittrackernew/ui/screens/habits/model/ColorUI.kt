package com.example.habittrackernew.ui.screens.habits.model

import com.example.data.model.HabitColor

enum class ColorUI {
    GREEN,
    BLUE,
    PURPLE,
}

fun ColorUI.toHabitColor() = when (this) {
    ColorUI.GREEN -> HabitColor.GREEN
    ColorUI.BLUE -> HabitColor.BLUE
    ColorUI.PURPLE -> HabitColor.PURPLE
}

fun HabitColor.toColorUI() = when (this) {
    HabitColor.GREEN -> ColorUI.GREEN
    HabitColor.BLUE -> ColorUI.BLUE
    HabitColor.PURPLE -> ColorUI.PURPLE
}
