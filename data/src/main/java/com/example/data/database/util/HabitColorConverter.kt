package com.example.data.database.util

import androidx.room.TypeConverter
import com.example.data.model.HabitColor

internal class HabitColorConverter {
    @TypeConverter
    fun stringToHabitColor(value: String?): HabitColor? = value?.uppercase()?.let(HabitColor::valueOf)

    @TypeConverter
    fun habitColorToString(habitColor: HabitColor?): String? = habitColor?.name?.lowercase()
}
