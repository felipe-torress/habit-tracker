package com.example.habittracker.ui.screens.habits.add.task.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.habittracker.R
import com.example.habittracker.ui.composables.buttons.PrimaryButton
import com.example.habittracker.ui.screens.habits.add.task.composables.TaskEntrySection
import com.example.habittracker.ui.utils.testTags.TestTagState

@Composable
fun ConfirmTaskSection(
    isConfirmEnabled: Boolean,
    isEditFlow: Boolean,
    onConfirmClick: () -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    TaskEntrySection(
        title = stringResource(R.string.task_entry_screen_days_of_week_section_title),
        testTagState = testTagState,
        modifier = modifier,
    ) {
        PrimaryButton(
            onClick = onConfirmClick,
            text = stringResource(
                if (isEditFlow) {
                    R.string.task_entry_screen_confirm_add_task_button_title_edit
                } else {
                    R.string.task_entry_screen_confirm_add_task_button_title_add
                }
            ),
            iconResId = R.drawable.ic_progress_24dp,
            enabled = isConfirmEnabled,
            testTagState = testTagState.action("ConfirmTaskEntry"),
        )
    }
}
