package com.example.data.mappers

import com.example.data.database.model.GoalEntity
import com.example.data.model.Goal

fun Goal.asGoalEntity() = GoalEntity(
    id = id,
    habitId = habitId,
    name = name,
    weeks = weeks,
    currentCompletions = currentCompletions,
    requiredCompletions = requiredCompletions,
    reward = reward,
    startDate = startDate,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun GoalEntity.asGoal() = Goal(
    id = id,
    habitId = habitId,
    name = name,
    weeks = weeks,
    currentCompletions = currentCompletions,
    requiredCompletions = requiredCompletions,
    reward = reward,
    startDate = startDate,
    createdAt = createdAt,
    updatedAt = updatedAt,
    associatedTask = emptyList()
)