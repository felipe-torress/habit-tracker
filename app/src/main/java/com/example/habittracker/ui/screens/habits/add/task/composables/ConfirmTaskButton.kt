package com.example.habittracker.ui.screens.habits.add.task.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.habittracker.R
import com.example.habittracker.ui.composables.buttons.PrimaryButton
import com.example.habittracker.ui.utils.testTags.TestTagState

@Composable
fun ConfirmTaskButton(
    isConfirmEnabled: Boolean,
    isEditFlow: Boolean,
    onConfirmClick: () -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    PrimaryButton(
        onClick = onConfirmClick,
        text = stringResource(
            if (isEditFlow) {
                R.string.task_entry_screen_confirm_add_task_button_title_edit
            } else {
                R.string.task_entry_screen_confirm_add_task_button_title_add
            },
        ),
        iconResId = R.drawable.ic_progress_24dp,
        enabled = isConfirmEnabled,
        testTagState = testTagState.action("ConfirmTaskEntry"),
        modifier = modifier,
    )
}
