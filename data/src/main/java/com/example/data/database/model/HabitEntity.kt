package com.example.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.model.HabitColor
import java.time.ZonedDateTime
import java.util.UUID

/**
 * Defines a Habit.
 * It has a one to many relationship with [HabitTaskEntity] and [GoalEntity]
 */
@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val color: HabitColor,
    @ColumnInfo(name = "created_at")
    val createdAt: ZonedDateTime,
    @ColumnInfo(name = "updated_at")
    val updatedAt: ZonedDateTime,
)
