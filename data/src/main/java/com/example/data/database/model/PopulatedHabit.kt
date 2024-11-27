package com.example.data.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.data.mappers.asGoal
import com.example.data.mappers.asHabitTask
import com.example.data.model.Habit

/**
 * External data layer representation of a fully populated Habit
 */
data class PopulatedHabit(
    @Embedded
    val habit: HabitEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "habit_id",
    )
    val goals: List<GoalEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "habit_id",
    )
    val tasks: List<HabitTaskEntity>,
)

fun PopulatedHabit.asHabit() =
    Habit(
        id = habit.id,
        name = habit.name,
        createdAt = habit.createdAt,
        updatedAt = habit.updatedAt,
        color = habit.color,
        goals = goals.map(GoalEntity::asGoal),
        tasks = tasks.map(HabitTaskEntity::asHabitTask),
    )
