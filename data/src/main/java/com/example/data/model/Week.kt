package com.example.data.model

import java.time.LocalDate

data class Week(
    val id: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val weekNumber: Int,
    val year: Int,
    val isCompleted: Boolean,
    val taskProgresses: List<TaskWeeklyProgress> = emptyList(),
)
