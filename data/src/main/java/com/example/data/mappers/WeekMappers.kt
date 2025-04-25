package com.example.data.mappers

import com.example.data.database.model.TaskWeeklyProgressEntity
import com.example.data.database.model.WeekEntity
import com.example.data.database.model.WeekWithTasksProgress
import com.example.data.model.Week

fun WeekEntity?.asWeek(): Week? {
    return this?.let {
        Week(
            id = id,
            startDate = startDate,
            endDate = endDate,
            weekNumber = weekNumber,
            year = year,
            isCompleted = isCompleted
        )
    }
}

fun WeekWithTasksProgress?.asWeek(): Week? {
    return this?.let {
        Week(
            id = week.id,
            startDate = week.startDate,
            endDate = week.endDate,
            weekNumber = week.weekNumber,
            year = week.year,
            isCompleted = week.isCompleted,
            taskProgresses = taskProgresses.map(TaskWeeklyProgressEntity::asTaskWeeklyProgress),
        )
    }
}

fun Week.asWeekEntity(): WeekEntity {
    return WeekEntity(
        id = id,
        startDate = startDate,
        endDate = endDate,
        weekNumber = weekNumber,
        year = year,
        isCompleted = isCompleted
    )
}
