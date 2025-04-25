package com.example.data.mappers

import com.example.data.database.model.TaskWeeklyProgressEntity
import com.example.data.model.TaskWeeklyProgress

fun TaskWeeklyProgressEntity?.asTaskWeeklyProgress(): TaskWeeklyProgress? {
    return this?.let {
        TaskWeeklyProgress(
            id = id,
            habitTaskId = habitTaskId,
            weekId = weekId,
            isCompleted = isCompleted,
            requiredDaysOfWeek = requiredDaysOfWeek,
            completedDaysOfWeek = completedDaysOfWeek,
        )
    }
}

fun TaskWeeklyProgressEntity.asTaskWeeklyProgress(): TaskWeeklyProgress {
    return TaskWeeklyProgress(
        id = id,
        habitTaskId = habitTaskId,
        weekId = weekId,
        isCompleted = isCompleted,
        requiredDaysOfWeek = requiredDaysOfWeek,
        completedDaysOfWeek = completedDaysOfWeek,
    )
}

fun TaskWeeklyProgress.asTaskWeeklyProgressEntity(): TaskWeeklyProgressEntity {
    return TaskWeeklyProgressEntity(
        id = id,
        habitTaskId = habitTaskId,
        weekId = weekId,
        isCompleted = isCompleted,
        requiredDaysOfWeek = requiredDaysOfWeek,
        completedDaysOfWeek = completedDaysOfWeek,
    )
}
