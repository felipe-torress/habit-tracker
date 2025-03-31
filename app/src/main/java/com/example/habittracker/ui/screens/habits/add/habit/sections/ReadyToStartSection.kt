package com.example.habittracker.ui.screens.habits.add.habit.sections

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.habittracker.R
import com.example.habittracker.ui.composables.buttons.PrimaryButton
import com.example.habittracker.ui.screens.habits.add.habit.composables.AddHabitSection
import com.example.habittracker.ui.utils.previews.DefaultBackgroundPreview
import com.example.habittracker.ui.utils.testTags.TestTagState

@Composable
fun ReadyToStartSection(
    onAddHabitClick: () -> Unit,
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
            testTagState = testTagState,
        )
    }
}

@Composable
private fun AddHabitButton(
    onClick: () -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    PrimaryButton(
        onClick = onClick,
        text = stringResource(id = R.string.add_habit_screen_add_habit_button_title),
        iconResId = R.drawable.ic_habits_24dp,
        modifier = modifier,
        testTagState = testTagState.action("AddHabit"),
    )
}

//region --- Preview ---
@DefaultBackgroundPreview
@Composable
private fun ReadyToStartSectionPreview() {
    ReadyToStartSection(
        onAddHabitClick = {},
        testTagState = TestTagState("ReadyToStartSection"),
    )
}
//endregion
