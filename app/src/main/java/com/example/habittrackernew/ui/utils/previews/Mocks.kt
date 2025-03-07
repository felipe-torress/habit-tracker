package com.example.habittrackernew.ui.utils.previews

import com.example.habittrackernew.ui.screens.habits.model.ColorUI
import com.example.habittrackernew.ui.screens.habits.model.HabitTaskUIData
import com.example.habittrackernew.ui.screens.habits.model.HabitUIData
import java.util.UUID

object Mocks {
    //region --- HabitTaskUIData ---
    val habitTaskUIData_1 = HabitTaskUIData(
        id = UUID.randomUUID().toString(),
        name = MockConstants.habitTask_title_1,
        time = MockConstants.localTime_11_00_AM,
        currentWeeklyCompletions = 2,
        requiredWeeklyCompletions = 10,
        daysOfWeek = MockConstants.daysOfWeek_1,
    )

    val habitTaskUIData_2 = habitTaskUIData_1.copy(
        name = MockConstants.habitTask_title_2,
        time = MockConstants.localTime_11_30_AM,
    )

    val habitTaskUIData_3 = habitTaskUIData_1.copy(
        name = MockConstants.habitTask_title_3,
        time = MockConstants.localTime_18_30_AM,
    )
    //endregion

    //region --- HabitUIData ---
    val habitUIData_1 = HabitUIData(
        id = UUID.randomUUID().toString(),
        name = MockConstants.habit_title_1,
        daysOfWeek = MockConstants.daysOfWeek_1,
        tasks = listOf(
            habitTaskUIData_1,
            habitTaskUIData_2,
            habitTaskUIData_3,
        ),
        color = ColorUI.BLUE,
    )

    val habitUIData_purple = habitUIData_1.copy(
        color = ColorUI.PURPLE,
    )

    val habitUIData_green = habitUIData_1.copy(
        color = ColorUI.GREEN,
    )
    //endregion
}
