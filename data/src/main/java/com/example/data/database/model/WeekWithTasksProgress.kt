package com.example.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

/**
 * External data layer representation of a fully populated Week
 */
data class WeekWithTasksProgress(
    @Embedded
    val week: WeekEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "week_id",
    )
    val taskProgresses: List<TaskWeeklyProgressEntity>,
)


