package com.example.habittrackernew.ui.screens.habits.model

import com.example.data.model.Habit
import com.example.data.model.HabitTask
import java.time.DayOfWeek

data class HabitUIData(
    val id: String,
    val name: String,
    val daysOfWeek: List<DayOfWeek>,
    val tasks: List<HabitTaskUIData>,
)

fun Habit.toHabitUIData(): HabitUIData {
    return HabitUIData(
        id = id,
        name = name,
        // TODO: Add days of week to Habit
        daysOfWeek = emptyList(),
        tasks = tasks.map(HabitTask::toHabitTaskUIData),
    )
}
