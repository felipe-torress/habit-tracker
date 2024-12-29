package com.example.habittrackernew.ui.screens.habits.add.sections

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.composables.colorpicker.ColorPicker
import com.example.habittrackernew.ui.screens.habits.add.composables.AddHabitSection
import com.example.habittrackernew.ui.screens.habits.model.ColorUI
import com.example.habittrackernew.ui.utils.testTags.TestTagState

@Composable
fun ColorPickerSection(
    selectedColor: ColorUI?,
    onColorClick: (ColorUI) -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier
) {
    AddHabitSection(
        title = stringResource(id = R.string.add_habit_screen_color_picker_section_title),
        testTagState = testTagState,
        modifier = modifier
    ) {
        ColorPicker(
            colors = listOf(ColorUI.GREEN, ColorUI.BLUE, ColorUI.PURPLE),
            onColorClick = onColorClick,
            selectedColor = selectedColor,
            testTagState = testTagState,
        )
    }
}
