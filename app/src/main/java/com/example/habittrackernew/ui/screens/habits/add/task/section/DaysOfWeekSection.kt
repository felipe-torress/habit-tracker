package com.example.habittrackernew.ui.screens.habits.add.task.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.composables.datetime.DayOfWeekPicker
import com.example.habittrackernew.ui.screens.habits.add.task.composables.AddTaskSection
import com.example.habittrackernew.ui.utils.testTags.TestTagState
import java.time.DayOfWeek

@Composable
fun DaysOfWeekSection(
    selectedDaysOfWeek: List<DayOfWeek>,
    onDayOfWeekClick: (DayOfWeek) -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    AddTaskSection(
        title = stringResource(R.string.add_task_screen_days_of_week_section_title),
        testTagState = testTagState,
        modifier = modifier,
    ) {
        DayOfWeekPicker(
            selectedDaysOfWeek = selectedDaysOfWeek,
            onDayOfWeekClick = onDayOfWeekClick,
        )
    }
}
