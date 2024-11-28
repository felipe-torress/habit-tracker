package com.example.habittrackernew.ui.utils.previews

import com.example.habittrackernew.ui.screens.habits.model.HabitTaskUIData
import java.util.UUID

object Mocks {
    //region --- HabitTaskUIData ---
    val habitTaskUIData_1 = HabitTaskUIData(
        id = UUID.randomUUID().toString(),
        name = MockConstants.habitTask_title_1,
        time = MockConstants.localTime_11_00_AM,
        currentWeeklyCompletions = 0,
        requiredWeeklyCompletions = 0,
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
}
