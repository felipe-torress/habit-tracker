package com.example.habittrackernew.ui.screens.habits.add.task.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.composables.buttons.PrimaryButton
import com.example.habittrackernew.ui.screens.habits.add.task.composables.AddTaskSection
import com.example.habittrackernew.ui.utils.testTags.TestTagState

@Composable
fun ConfirmAddTaskSection(
    isConfirmEnabled: Boolean,
    onConfirmClick: () -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier
) {
    AddTaskSection(
        title = stringResource(R.string.add_task_screen_days_of_week_section_title),
        testTagState = testTagState,
        modifier = modifier
    ) {
        PrimaryButton(
            onClick = onConfirmClick,
            text = stringResource(R.string.add_task_screen_confirm_add_task_button_title),
            iconResId = R.drawable.ic_progress_24dp,
            enabled = isConfirmEnabled
        )
    }
}
