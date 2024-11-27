package com.example.data.mappers

import com.example.data.database.model.HabitEntity
import com.example.data.model.Habit

fun Habit.asHabitEntity() =
    HabitEntity(
        id = id,
        name = name,
        createdAt = createdAt,
        updatedAt = updatedAt,
        color = color,
    )

fun HabitEntity.asHabit() =
    Habit(
        id = id,
        name = name,
        createdAt = createdAt,
        updatedAt = updatedAt,
        color = color,
        tasks = emptyList(),
        goals = emptyList(),
    )
