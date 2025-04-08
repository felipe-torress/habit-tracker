package com.example.data.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.data.mappers.asHabitTask
import com.example.data.model.Goal

// TODO: Check improvements: https://stackoverflow.com/questions/58520141/room-relation-returning-only-the-first-item-of-the-table

/**
 External data layer representation of a fully populated Goal
 */
data class PopulatedGoal(
    @Embedded
    val goal: GoalEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy =
            Junction(
                value = GoalHabitTaskCrossRef::class,
                parentColumn = "goal_id",
                entityColumn = "habit_task_id",
            ),
    )
    val habitTasks: List<HabitTaskEntity>,
)

fun PopulatedGoal.asGoal() =
    Goal(
        id = goal.id,
        habitId = goal.habitId,
        name = goal.name,
        weeks = goal.weeks,
        currentCompletions = goal.currentCompletions,
        requiredCompletions = goal.requiredCompletions,
        reward = goal.reward,
        startDate = goal.startDate,
        createdAt = goal.createdAt,
        updatedAt = goal.updatedAt,
        associatedTask = habitTasks.map(HabitTaskEntity::asHabitTask),
    )
