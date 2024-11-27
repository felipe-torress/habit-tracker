package com.example.data.mappers

import com.example.data.database.model.HabitTaskEntity
import com.example.data.model.HabitTask

fun HabitTask.asHabitTaskEntity() =
    HabitTaskEntity(
        id = id,
        habitId = habitId,
        name = name,
        time = time,
        currentWeeklyCompletions = currentWeeklyCompletions,
        requiredWeeklyCompletions = requiredWeeklyCompletions,
        daysOfWeek = daysOfWeek,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

fun HabitTaskEntity.asHabitTask() =
    HabitTask(
        id = id,
        habitId = habitId,
        name = name,
        time = time,
        currentWeeklyCompletions = currentWeeklyCompletions,
        requiredWeeklyCompletions = requiredWeeklyCompletions,
        daysOfWeek = daysOfWeek,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
