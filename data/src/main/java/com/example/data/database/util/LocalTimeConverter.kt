package com.example.data.database.util

import androidx.room.TypeConverter
import java.time.LocalTime
import java.time.format.DateTimeFormatter

internal class LocalTimeConverter {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME

    @TypeConverter
    fun isoStringToLocalTime(isoString: String?): LocalTime? = isoString?.let { LocalTime.parse(it, formatter) }

    @TypeConverter
    fun localTimeToIsoString(localTime: LocalTime?): String? = localTime?.format(formatter)
}