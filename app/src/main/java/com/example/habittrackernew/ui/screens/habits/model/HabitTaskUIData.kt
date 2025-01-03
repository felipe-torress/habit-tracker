package com.example.habittrackernew.ui.screens.habits.model

import com.example.data.model.HabitTask
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.ZonedDateTime

data class HabitTaskUIData(
    val id: String,
    val name: String,
    val time: LocalTime,
    val currentWeeklyCompletions: Int,
    val requiredWeeklyCompletions: Int,
    val daysOfWeek: List<DayOfWeek>,
)

fun HabitTaskUIData.toHabitTask(
    habitId: String,
    createdAt: ZonedDateTime,
    updatedAt: ZonedDateTime,
): HabitTask {
    return HabitTask(
        id = id,
        name = name,
        time = time,
        currentWeeklyCompletions = currentWeeklyCompletions,
        requiredWeeklyCompletions = requiredWeeklyCompletions,
        daysOfWeek = daysOfWeek,
        habitId = habitId,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}

fun HabitTask.toHabitTaskUIData(): HabitTaskUIData {
    return HabitTaskUIData(
        id = id,
        name = name,
        time = time,
        currentWeeklyCompletions = currentWeeklyCompletions,
        requiredWeeklyCompletions = requiredWeeklyCompletions,
        daysOfWeek = daysOfWeek,
    )
}
