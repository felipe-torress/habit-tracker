package com.example.habittrackernew.ui.screens.habits.model

import java.time.DayOfWeek

data class HabitUIData(
    val id: String,
    val name: String,
    val daysOfWeek: List<DayOfWeek>,
    val tasks: List<HabitTaskUIData>,
)
