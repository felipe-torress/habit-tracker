package com.example.habittrackernew.ui.composables.colorpicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.habittrackernew.ui.screens.habits.model.ColorUI
import com.example.habittrackernew.ui.utils.testTags.TestTagState

@Composable
fun ColorPicker(
    colors: List<ColorUI>,
    onColorClick: (ColorUI) -> Unit,
    selectedColor: ColorUI?,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .testTag("${testTagState.origin}ColorPicker"),
    ) {
        colors.forEachIndexed { index, color ->
            ColorPickerItem(
                color = color,
                onColorClick = onColorClick,
                isSelected = color == selectedColor,
                testTagState = testTagState.index(index),
                modifier = modifier,
            )
        }
    }
}

//region MARK: --- Preview ---
private data class ColorPickerPreviewData(
    val colors: List<ColorUI> = listOf(ColorUI.GREEN, ColorUI.BLUE, ColorUI.PURPLE),
    val selectedColor: ColorUI? = null,
)

private class ColorPickerPreviewProvider : PreviewParameterProvider<ColorPickerPreviewData> {
    override val values = sequenceOf(
        ColorPickerPreviewData(),
    )
}

@Preview
@Composable
private fun ColorPickerPreview(
    @PreviewParameter(ColorPickerPreviewProvider::class) previewData: ColorPickerPreviewData,
) {
    ColorPicker(
        colors = previewData.colors,
        selectedColor = previewData.selectedColor,
        onColorClick = {},
        testTagState = TestTagState("ColorPicker"),
    )
}
//endregion
