package com.example.habittrackernew.ui.screen.habits.models

import java.time.DayOfWeek
import java.time.LocalTime

data class HabitTaskUIData(
    val id: String,
    val name: String,
    val time: LocalTime,
    val currentWeeklyCompletions: Int,
    val requiredWeeklyCompletions: Int,
    val daysOfWeek: List<DayOfWeek>,
)
