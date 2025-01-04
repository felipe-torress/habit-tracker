package com.example.habittrackernew.ui.screens.habits.details.sections

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.screens.habits.details.composables.HabitTaskCard
import com.example.habittrackernew.ui.screens.habits.details.composables.habitDetailsSection
import com.example.habittrackernew.ui.screens.habits.model.ColorUI
import com.example.habittrackernew.ui.screens.habits.model.HabitTaskUIData
import com.example.habittrackernew.ui.utils.testTags.TestTagState

fun LazyListScope.tasksSection(
    tasks: List<HabitTaskUIData>,
    color: ColorUI,
    onEditTaskClick: (taskId: String) -> Unit,
    testTagState: TestTagState,
) {
    habitDetailsSection(
        titleResId = R.string.habit_details_screen_tasks_section_title,
        descriptionResId = R.string.habit_details_screen_tasks_section_description,
        isFirstSection = true, // TODO: Change it when goals section is done
        color = color,
    ) {
        itemsIndexed(items = tasks) { index, task ->
            val isFirstItem = index == 0

            HabitTaskCard(
                task = task,
                color = color,
                onEditClick = onEditTaskClick,
                testTagState = testTagState.index(index),
                modifier = Modifier.padding(top = if (isFirstItem) 16.dp else 8.dp)
            )
        }
    }
}
