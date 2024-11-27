package com.example.data.database.util

import androidx.room.TypeConverter
import java.time.DayOfWeek

internal class DayOfWeekListConverter {
    @TypeConverter
    fun fromDayOfWeekList(dayOfWeekList: List<DayOfWeek>?): String? {
        return dayOfWeekList?.joinToString(",") { it.name }
    }

    @TypeConverter
    fun toDayOfWeekList(dayOfWeekString: String?): List<DayOfWeek>? {
        return dayOfWeekString?.split(",")?.map { DayOfWeek.valueOf(it) }
    }
}
