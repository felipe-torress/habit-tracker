package com.example.habittracker.ui.screens.habits.add.habit.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.habittracker.R
import com.example.habittracker.ui.composables.buttons.SecondaryButton
import com.example.habittracker.ui.screens.habits.add.habit.composables.AddHabitSection
import com.example.habittracker.ui.screens.habits.add.habit.composables.HabitTaskCard
import com.example.habittracker.ui.screens.habits.model.HabitTaskUIData
import com.example.habittracker.ui.utils.testTags.TestTagState

@Composable
fun TasksSection(
    tasks: List<HabitTaskUIData>,
    testTagState: TestTagState,
    onAddTaskClick: () -> Unit,
    onEditTaskClick: (taskId: String) -> Unit,
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
                    onEditClick = onEditTaskClick,
                    testTagState = testTagState.index(index),
                )
            }

            AddTaskButton(
                onClick = onAddTaskClick,
                testTagState = testTagState,
            )
        }
    }
}

@Composable
fun AddTaskButton(
    onClick: () -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    SecondaryButton(
        onClick = onClick,
        text = stringResource(id = R.string.add_habit_screen_add_task_button_title),
        iconResId = R.drawable.ic_progress_24dp,
        modifier = modifier,
        testTagState = testTagState.action("AddTask"),
    )
}
