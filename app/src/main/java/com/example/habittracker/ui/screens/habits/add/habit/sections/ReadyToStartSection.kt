package com.example.habittracker.ui.screens.habits.add.habit.sections

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.habittracker.R
import com.example.habittracker.ui.composables.buttons.PrimaryButton
import com.example.habittracker.ui.screens.habits.add.habit.composables.AddHabitSection
import com.example.habittracker.ui.utils.testTags.TestTagState

@Composable
fun ReadyToStartSection(
    onAddHabitClick: () -> Unit,
    isAddHabitEnabled: Boolean,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    AddHabitSection(
        title = stringResource(id = R.string.add_habit_screen_ready_to_start_section_title),
        testTagState = testTagState.section("ReadyToStartSection"),
        modifier = modifier,
    ) {
        AddHabitButton(
            onClick = onAddHabitClick,
            enabled = isAddHabitEnabled,
            testTagState = testTagState,
        )
    }
}

@Composable
private fun AddHabitButton(
    onClick: () -> Unit,
    enabled: Boolean,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    PrimaryButton(
        onClick = onClick,
        enabled = enabled,
        text = stringResource(id = R.string.add_habit_screen_add_habit_button_title),
        iconResId = R.drawable.ic_habits_24dp,
        modifier = modifier,
        testTagState = testTagState.action("AddHabit"),
    )
}

//region --- Preview ---
data class ReadyToStartSectionPreviewData(val isAddHabitEnabled: Boolean = true)

private class ReadyToStartSectionPreviewProvider : PreviewParameterProvider<ReadyToStartSectionPreviewData> {
    override val values = sequenceOf(
        // Enabled Button
        ReadyToStartSectionPreviewData(),
        // Disabled Button
        ReadyToStartSectionPreviewData(isAddHabitEnabled = false),
    )
}

@Preview
@Composable
fun ReadyToStartSectionPreview(
    @PreviewParameter(ReadyToStartSectionPreviewProvider::class) previewData: ReadyToStartSectionPreviewData,
) {
    ReadyToStartSection(
        onAddHabitClick = {},
        isAddHabitEnabled = previewData.isAddHabitEnabled,
        testTagState = TestTagState("ReadyToStartSection"),
    )
}
//endregion
