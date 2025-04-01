package com.example.habittracker.ui.screens.habits.add.task.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.habittracker.R
import com.example.habittracker.ui.composables.datetime.DayOfWeekPicker
import com.example.habittracker.ui.screens.habits.add.task.composables.TaskEntrySection
import com.example.habittracker.ui.utils.testTags.TestTagState
import java.time.DayOfWeek

@Composable
fun DaysOfWeekSection(
    selectedDaysOfWeek: List<DayOfWeek>,
    onDayOfWeekClick: (DayOfWeek) -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    TaskEntrySection(
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
