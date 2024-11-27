package com.example.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Cross reference for many-to-many relationship between [GoalEntity] and [HabitTaskEntity]
 */
@Entity(
    tableName = "goals_habit_tasks",
    primaryKeys = ["goal_id", "habit_task_id"],
    foreignKeys = [
        ForeignKey(
            entity = GoalEntity::class,
            parentColumns = ["id"],
            childColumns = ["goal_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = HabitTaskEntity::class,
            parentColumns = ["id"],
            childColumns = ["habit_task_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["goal_id"]),
        Index(value = ["habit_task_id"]),
    ],
)
data class GoalHabitTaskCrossRef(
    @ColumnInfo(name = "goal_id")
    val goalId: String,
    @ColumnInfo(name = "habit_task_id")
    val habitTaskId: String,
)
