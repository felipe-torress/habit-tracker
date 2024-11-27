package com.example.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.data.model.Goal
import java.time.ZonedDateTime
import java.util.UUID

/**
 * Defines a Habit Goal.
 * It has a many to many relationship with [HabitTaskEntity]
 */
@Entity(
    tableName = "goals",
    foreignKeys = [
        ForeignKey(
            entity = HabitEntity::class,
            parentColumns = ["id"],
            childColumns = ["habit_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GoalEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "habit_id")
    val habitId: String,
    val name: String,
    val weeks: Int,
    @ColumnInfo(name = "current_completions")
    val currentCompletions: Int,
    @ColumnInfo(name = "required_completions")
    val requiredCompletions: Int,
    val reward: String,
    @ColumnInfo(name = "start_date")
    val startDate: ZonedDateTime,
    @ColumnInfo(name = "created_at")
    val createdAt: ZonedDateTime,
    @ColumnInfo(name = "updated_at")
    val updatedAt: ZonedDateTime,
)
