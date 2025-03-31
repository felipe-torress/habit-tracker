package com.example.habittracker.ui.screens.habits.add.habit.sections

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.habittracker.R
import com.example.habittracker.ui.composables.colorpicker.ColorPicker
import com.example.habittracker.ui.screens.habits.add.habit.composables.AddHabitSection
import com.example.habittracker.ui.screens.habits.model.ColorUI
import com.example.habittracker.ui.utils.testTags.TestTagState

@Composable
fun ColorPickerSection(
    selectedColor: ColorUI?,
    onColorClick: (ColorUI) -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    AddHabitSection(
        title = stringResource(id = R.string.add_habit_screen_color_picker_section_title),
        testTagState = testTagState,
        modifier = modifier,
    ) {
        ColorPicker(
            colors = listOf(ColorUI.GREEN, ColorUI.BLUE, ColorUI.PURPLE),
            onColorClick = onColorClick,
            selectedColor = selectedColor,
            testTagState = testTagState,
        )
    }
}
