package com.example.data.model

import java.time.DayOfWeek
import java.time.LocalTime
import java.time.ZonedDateTime

data class HabitTask(
    val id: String,
    val habitId: String,
    val name: String,
    val time: LocalTime,
    val currentWeeklyCompletions: Int,
    val requiredWeeklyCompletions: Int,
    val daysOfWeek: List<DayOfWeek>,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
)