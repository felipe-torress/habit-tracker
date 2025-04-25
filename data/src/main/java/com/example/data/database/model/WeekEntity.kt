package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.UUID

@Entity(tableName = "weeks")
data class WeekEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val startDate: LocalDate,
    val endDate: LocalDate,
    val weekNumber: Int,
    val year: Int,
    val isCompleted: Boolean = false
)
