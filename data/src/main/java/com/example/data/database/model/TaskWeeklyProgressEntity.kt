package com.example.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.util.UUID

@Entity(
    tableName = "week_progress",
    foreignKeys = [
        ForeignKey(
            entity = HabitTaskEntity::class,
            parentColumns = ["id"],
            childColumns = ["habit_task_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = WeekEntity::class,
            parentColumns = ["id"],
            childColumns = ["week_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index("habit_task_id"),
        Index("week_id"),
        Index(value = ["habit_task_id", "week_id"], unique = true)
    ],
)
data class TaskWeeklyProgressEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "habit_task_id") val habitTaskId: String,
    @ColumnInfo(name = "week_id") val weekId: String,
    val requiredDaysOfWeek: List<DayOfWeek>,
    val completedDaysOfWeek: List<DayOfWeek>,
    val isCompleted: Boolean = false,
)
