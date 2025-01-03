package com.example.habittrackernew.ui.screens.habits.add.habit.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.composables.buttons.SecondaryButton
import com.example.habittrackernew.ui.screens.habits.add.habit.composables.AddHabitSection
import com.example.habittrackernew.ui.screens.habits.add.habit.composables.HabitTaskCard
import com.example.habittrackernew.ui.screens.habits.model.HabitTaskUIData
import com.example.habittrackernew.ui.utils.testTags.TestTagState

@Composable
fun TasksSection(
    tasks: List<HabitTaskUIData>,
    testTagState: TestTagState,
    onAddTaskClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AddHabitSection(
        title = stringResource(id = R.string.add_habit_screen_add_task_section_title),
        description = stringResource(id = R.string.add_habit_screen_add_task_section_description),
        testTagState = testTagState,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            tasks.forEachIndexed { index, task ->
                HabitTaskCard(
                    task = task,
                    onEditClick = {},
                    testTagState = testTagState.index(index),
                )
            }

            AddTaskButton(onClick = onAddTaskClick)
        }
    }
}

@Composable
fun AddTaskButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SecondaryButton(
        onClick = onClick,
        text = stringResource(id = R.string.add_habit_screen_add_task_button_title),
        iconResId = R.drawable.ic_progress_24dp,
        modifier = modifier,
    )
}
