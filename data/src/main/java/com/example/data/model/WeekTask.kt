package com.example.data.model

import java.time.DayOfWeek
import java.time.LocalTime

data class WeekTask(
    val taskId: String,
    val taskName: String,
    val habitId: String,
    val habitName: String,
    val dayOfWeek: DayOfWeek,
    val time: LocalTime,
    val habitColor: HabitColor,
)
