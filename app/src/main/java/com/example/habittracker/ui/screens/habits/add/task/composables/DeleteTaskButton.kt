package com.example.habittracker.ui.screens.habits.add.task.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.habittracker.R
import com.example.habittracker.ui.composables.buttons.NegativeButton
import com.example.habittracker.ui.utils.testTags.TestTagState

@Composable
fun DeleteTaskButton(
    onClick: () -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    NegativeButton(
        onClick = onClick,
        title = stringResource(R.string.task_entry_screen_delete_task_button_title),
        iconResId = R.drawable.ic_trash_24dp,
        testTagState = testTagState.action("DeleteTask"),
        modifier = modifier,
    )
}
