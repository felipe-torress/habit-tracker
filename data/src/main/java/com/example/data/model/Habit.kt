package com.example.data.model

import java.time.ZonedDateTime

data class Habit(
    val id: String,
    val name: String,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val color: HabitColor,
    val goals: List<Goal>,
    val tasks: List<HabitTask>,
)

enum class HabitColor {
    BLUE,
    GREEN,
    PURPLE,
}
