package com.example.habittrackernew.ui.composables.colorpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.habittrackernew.ui.screens.habits.model.ColorUI
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.utils.compose.thenIf
import com.example.habittrackernew.ui.utils.testTags.TestTagState

@Composable
fun ColorPickerItem(
    color: ColorUI,
    onColorClick: (ColorUI) -> Unit,
    isSelected: Boolean,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(16.dp)

    val (backgroundColor, borderColor) = when (color) {
        ColorUI.GREEN -> HabitTrackerColors.green300 to HabitTrackerColors.green600
        ColorUI.BLUE -> HabitTrackerColors.blue300 to HabitTrackerColors.blue600
        ColorUI.PURPLE -> HabitTrackerColors.purple200 to HabitTrackerColors.purple500
    }

    Box(
        modifier = modifier
            .size(48.dp)
            .thenIf(isSelected) {
                border(2.dp, borderColor, shape)
            }
            .clip(shape)
            .background(backgroundColor)
            .clickable { onColorClick(color) }
            .testTag("${testTagState.origin}ColorPickerItem${testTagState.index}"),
    )
}

//region MARK: --- Preview ---
data class ColorPickerItemPreviewData(
    val color: ColorUI = ColorUI.GREEN,
    val isSelected: Boolean = false,
)

private class ColorPickerItemPreviewProvider : PreviewParameterProvider<ColorPickerItemPreviewData> {
    override val values = sequenceOf(
        // Green - Not Selected
        ColorPickerItemPreviewData(),
        // Green - Selected
        ColorPickerItemPreviewData(isSelected = true),
        // Blue - Not Selected
        ColorPickerItemPreviewData(color = ColorUI.BLUE),
        // Blue - Selected
        ColorPickerItemPreviewData(color = ColorUI.BLUE, isSelected = true),
        // Purple - Not Selected
        ColorPickerItemPreviewData(color = ColorUI.PURPLE),
        // Purple - Selected
        ColorPickerItemPreviewData(color = ColorUI.PURPLE, isSelected = true),
    )
}

@Preview
@Composable
fun ColorPickerItemPreview(
    @PreviewParameter(ColorPickerItemPreviewProvider::class) previewData: ColorPickerItemPreviewData,
) {
    ColorPickerItem(
        color = previewData.color,
        isSelected = previewData.isSelected,
        onColorClick = {},
        testTagState = TestTagState("ColorPickerItem"),
    )
}
//endregion
