package com.example.habittrackernew.ui.utils.datetime

import com.example.data.model.HabitTask
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

private const val DEFAULT_TIME_FORMAT = "HH:mm"
private val defaultLocale = Locale("pt", "BR")

fun LocalTime.toLocalizedTime(): String = this.format(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT))

fun List<DayOfWeek>.toDaysOfWeekText(): String {
    val sortedDays = this.sortedBy { it.ordinal }
    val displayNames = sortedDays.map { it.getDisplayName(TextStyle.SHORT, defaultLocale) }
    return displayNames.joinToString { it.removeSuffix(".").capitalize() }
}

fun DayOfWeek.toDayOfWeekInitial(): String = this.getDisplayName(TextStyle.NARROW_STANDALONE, defaultLocale)

fun String.capitalize() = replaceFirstChar { it.uppercase() }

fun getLocalTime(
    hour: Int,
    minute: Int,
): LocalTime = LocalTime.of(hour, minute)

fun List<HabitTask>.getDaysOfWeek(): List<DayOfWeek> {
    return this
        .map { it.daysOfWeek }
        .flatten()
        .distinct()
        .sortedBy { it.ordinal }
}
