package com.example.habittracker.ui.screens.habits.details.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.habittracker.R
import com.example.habittracker.ui.composables.buttons.ColorfulButton
import com.example.habittracker.ui.screens.habits.details.composables.HabitTaskCard
import com.example.habittracker.ui.screens.habits.details.composables.habitDetailsSection
import com.example.habittracker.ui.screens.habits.model.ColorUI
import com.example.habittracker.ui.screens.habits.model.HabitTaskUIData
import com.example.habittracker.ui.theme.HabitTrackerColors
import com.example.habittracker.ui.theme.HabitTrackerTypography
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
        if (tasks.isNotEmpty()) {
            tasksList(
                tasks = tasks,
                color = color,
                onEditTaskClick = onEditTaskClick,
                testTagState = testTagState,
                onAddTask = onAddTask
            )
        } else {
            addTaskWarning(
                color = color,
                onAddTaskClick = onAddTask,
                testTagState = testTagState,
            )
        }
    }
}

private fun LazyListScope.tasksList(
    tasks: List<HabitTaskUIData>,
    color: ColorUI,
    onEditTaskClick: (taskId: String) -> Unit,
    testTagState: TestTagState,
    onAddTask: () -> Unit
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

    addTaskButton(
        onAddTask = onAddTask,
        color = color,
    )
}

fun LazyListScope.addTaskWarning(
    color: ColorUI,
    onAddTaskClick: () -> Unit,
    testTagState: TestTagState,
) {
    item {
        val backgroundColor = when (color) {
            ColorUI.GREEN -> HabitTrackerColors.green50
            ColorUI.BLUE -> HabitTrackerColors.blue50
            ColorUI.PURPLE -> HabitTrackerColors.purple50
        }

        val (textColor, iconColor) = when (color) {
            ColorUI.GREEN -> HabitTrackerColors.green900 to HabitTrackerColors.green800
            ColorUI.BLUE -> HabitTrackerColors.blue900 to HabitTrackerColors.blue800
            ColorUI.PURPLE -> HabitTrackerColors.purple900 to HabitTrackerColors.purple800
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(backgroundColor)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.CenterHorizontally),
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_alert_16dp),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(iconColor),
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = stringResource(R.string.habit_list_screen_no_tasks_for_habit_warning_message),
                    style = HabitTrackerTypography.bodySmall,
                    color = textColor,
                )
            }

            ColorfulButton(
                onClick = onAddTaskClick,
                color = color,
                iconResId = R.drawable.ic_progress_24dp,
                text = stringResource(R.string.add_habit_screen_add_task_button_title),
                testTagState = testTagState.action("AddTask"),
            )
        }
    }
}

fun LazyListScope.addTaskButton(
    onAddTask: () -> Unit,
    color: ColorUI,
) {
    item {
        val backgroundColor = when (color) {
            ColorUI.GREEN -> HabitTrackerColors.green500
            ColorUI.BLUE -> HabitTrackerColors.blue500
            ColorUI.PURPLE -> HabitTrackerColors.purple500
        }

        val rippleColor = when (color) {
            ColorUI.GREEN -> HabitTrackerColors.green700
            ColorUI.BLUE -> HabitTrackerColors.blue700
            ColorUI.PURPLE -> HabitTrackerColors.purple700
        }

        val iconColor = when (color) {
            ColorUI.GREEN -> HabitTrackerColors.green50
            ColorUI.BLUE -> HabitTrackerColors.blue50
            ColorUI.PURPLE -> HabitTrackerColors.purple50
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor)
                .clickable(
                    interactionSource = rememberInteractionsSource(),
                    indication = ripple(color = rippleColor),
                    onClick = onAddTask,
                )
                .size(48.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus_16dp),
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(18.dp),
            )
        }
    }
}
