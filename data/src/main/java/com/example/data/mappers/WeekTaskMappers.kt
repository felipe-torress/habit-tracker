package com.example.data.mappers

import com.example.data.model.HabitColor
import com.example.data.model.HabitTask
import com.example.data.model.WeekTask
import java.time.DayOfWeek

fun HabitTask.asWeekTask(
    dayOfWeek: DayOfWeek,
    habitId: String,
    habitName: String,
    habitColor: HabitColor,
): WeekTask {
    return WeekTask(
        taskId = id,
        taskName = name,
        time = time,
        dayOfWeek = dayOfWeek,
        habitId = habitId,
        habitName = habitName,
        habitColor = habitColor,
    )
}

