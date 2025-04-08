package com.example.data.mappers

import com.example.data.database.model.HabitEntity
import com.example.data.database.model.PopulatedHabit
import com.example.data.model.Goal
import com.example.data.model.Habit
import com.example.data.model.HabitTask

fun Habit.asHabitEntity() =
    HabitEntity(
        id = id,
        name = name,
        createdAt = createdAt,
        updatedAt = updatedAt,
        color = color,
    )

fun Habit.asPopulatedHabit() =
    PopulatedHabit(
        habit = asHabitEntity(),
        goals = goals.map(Goal::asGoalEntity),
        tasks = tasks.map(HabitTask::asHabitTaskEntity),
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
