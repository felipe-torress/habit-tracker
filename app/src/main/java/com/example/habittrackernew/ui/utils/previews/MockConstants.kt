package com.example.habittrackernew.ui.utils.previews

import java.time.DayOfWeek
import java.time.LocalTime

object MockConstants {
    //region --- Days of Week ---
    val daysOfWeek_1: List<DayOfWeek> = listOf(
        DayOfWeek.MONDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.FRIDAY,
    )

    val daysOfWeek_weekDays: List<DayOfWeek> = listOf(
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
    )

    val daysOfWeek_allDays: List<DayOfWeek> = listOf(
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY,
        DayOfWeek.SUNDAY,
    )
    //endregion

    //region --- LocalTime ---
    val localTime_11_00_AM = LocalTime.of(11, 0)
    val localTime_11_30_AM = LocalTime.of(11, 30)
    val localTime_18_30_AM = LocalTime.of(18, 30)
    //endregion

    //region --- Strings ---

    // Habit
    val habit_title_1: String = "Execitar Regularmente"

    // Habit Task
    val habitTask_title_1: String = "Fazer pré-treino"
    val habitTask_title_2: String = "Ir à Academia"
    val habitTask_title_3: String = "Fazer Yoga"

    //endregion
}
