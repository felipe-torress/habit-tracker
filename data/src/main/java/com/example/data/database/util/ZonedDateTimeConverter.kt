package com.example.data.database.util

import androidx.room.TypeConverter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

internal class ZonedDateTimeConverter {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME

    @TypeConverter
    fun isoStringToZonedDateTime(isoString: String?): ZonedDateTime? = isoString?.let { ZonedDateTime.parse(it, formatter) }

    @TypeConverter
    fun zonedDateTimeToIsoString(zonedDateTime: ZonedDateTime?): String? = zonedDateTime?.format(formatter)
}
