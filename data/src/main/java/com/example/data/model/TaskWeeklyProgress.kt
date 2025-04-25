package com.example.data.model

import java.time.DayOfWeek

data class TaskWeeklyProgress(
    val id: String,
    val habitTaskId: String,
    val weekId: String,
    val isCompleted: Boolean = false,
    val requiredDaysOfWeek: List<DayOfWeek>,
    val completedDaysOfWeek: List<DayOfWeek>,
)
