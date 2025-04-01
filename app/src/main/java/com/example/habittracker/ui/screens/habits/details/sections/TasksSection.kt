package com.example.habittracker.ui.screens.habits.details.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.habittracker.R
import com.example.habittracker.ui.screens.habits.details.composables.HabitTaskCard
import com.example.habittracker.ui.screens.habits.details.composables.habitDetailsSection
import com.example.habittracker.ui.screens.habits.model.ColorUI
import com.example.habittracker.ui.screens.habits.model.HabitTaskUIData
import com.example.habittracker.ui.theme.HabitTrackerColors
import com.example.habittracker.ui.utils.compose.rememberInteractionsSource
import com.example.habittracker.ui.utils.testTags.TestTagState

fun LazyListScope.tasksSection(
    tasks: List<HabitTaskUIData>,
    color: ColorUI,
    onEditTaskClick: (taskId: String) -> Unit,
    onAddTask: () -> Unit,
    testTagState: TestTagState,
) {
    habitDetailsSection(
        titleResId = R.string.habit_details_screen_tasks_section_title,
        descriptionResId = R.string.habit_details_screen_tasks_section_description,
        // TODO: Change it when goals section is done
        isFirstSection = true,
        color = color,
    ) {
        itemsIndexed(items = tasks) { index, task ->
            val isFirstItem = index == 0

            HabitTaskCard(
                task = task,
                color = color,
                onEditClick = onEditTaskClick,
                testTagState = testTagState.index(index),
                modifier = Modifier.padding(top = if (isFirstItem) 16.dp else 8.dp),
            )
        }

        addTaskButton(onAddTask)
    }
}

fun LazyListScope.addTaskButton(
    onAddTask: () -> Unit,
) {
    item {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(HabitTrackerColors.blue500)
                .clickable(
                    interactionSource = rememberInteractionsSource(),
                    indication = ripple(color = HabitTrackerColors.green700),
                    onClick = onAddTask
                )
                .size(48.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus_16dp),
                contentDescription = null,
                tint = HabitTrackerColors.blue50,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
