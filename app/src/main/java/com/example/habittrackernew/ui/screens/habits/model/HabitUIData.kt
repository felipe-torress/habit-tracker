package com.example.habittrackernew.ui.screens.habits.model

import com.example.data.model.Habit
import com.example.data.model.HabitTask
import com.example.habittrackernew.ui.utils.datetime.getDaysOfWeek
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
        daysOfWeek = tasks.getDaysOfWeek(),
        tasks = tasks.map(HabitTask::toHabitTaskUIData),
    )
}
