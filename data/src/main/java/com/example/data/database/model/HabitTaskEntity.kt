package com.example.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.ZonedDateTime
import java.util.UUID

/**
 * Defines a Habit Task
 * It has a many to many relationship with [GoalEntity]
 */
@Entity(
    tableName = "habit_tasks",
    foreignKeys = [
        ForeignKey(
            entity = HabitEntity::class,
            parentColumns = ["id"],
            childColumns = ["habit_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HabitTaskEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "habit_id")
    val habitId: String,
    val name: String,
    val time: LocalTime,
    @ColumnInfo(name = "current_weekly_completions")
    val currentWeeklyCompletions: Int,
    @ColumnInfo(name = "required_weekly_completions")
    val requiredWeeklyCompletions: Int,
    @ColumnInfo(name = "days_of_week")
    val daysOfWeek: List<DayOfWeek>,
    @ColumnInfo(name = "created_at")
    val createdAt: ZonedDateTime,
    @ColumnInfo(name = "updated_at")
    val updatedAt: ZonedDateTime,
)
