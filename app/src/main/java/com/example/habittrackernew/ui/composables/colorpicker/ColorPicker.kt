package com.example.habittrackernew.ui.composables.colorpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.habittrackernew.ui.screens.habits.model.ColorUI
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.utils.compose.thenIf

@Composable
fun ColorPicker(
    color: ColorUI,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(16.dp)

    val (backgroundColor, borderColor) = when (color) {
        ColorUI.GREEN -> HabitTrackerColors.green300 to HabitTrackerColors.green500
        ColorUI.BLUE -> HabitTrackerColors.blue300 to HabitTrackerColors.blue500
        ColorUI.PURPLE -> HabitTrackerColors.purple200 to HabitTrackerColors.purple400
    }

    Box(
        modifier = modifier
            .size(48.dp)
            .thenIf(isSelected) {
                border(2.dp, borderColor, shape)
            }
            .clip(shape)
            .background(backgroundColor)
    )
}

//region MARK: --- Preview ---
data class ColorPickerPreviewData(
    val color: ColorUI = ColorUI.GREEN,
    val isSelected: Boolean = false,
)

private class ColorPickerPreviewProvider : PreviewParameterProvider<ColorPickerPreviewData> {
    override val values = sequenceOf(
        // Green - Not Selected
        ColorPickerPreviewData(),

        // Green - Selected
        ColorPickerPreviewData(isSelected = true),

        // Blue - Not Selected
        ColorPickerPreviewData(color = ColorUI.BLUE),

        // Blue - Selected
        ColorPickerPreviewData(color = ColorUI.BLUE, isSelected = true),

        // Purple - Not Selected
        ColorPickerPreviewData(color = ColorUI.PURPLE),

        // Purple - Selected
        ColorPickerPreviewData(color = ColorUI.PURPLE, isSelected = true),

        )
}

@Preview
@Composable
fun ColorPickerPreview(@PreviewParameter(ColorPickerPreviewProvider::class) previewData: ColorPickerPreviewData) {
    ColorPicker(
        color = previewData.color,
        isSelected = previewData.isSelected,
    )
}
//endregion
