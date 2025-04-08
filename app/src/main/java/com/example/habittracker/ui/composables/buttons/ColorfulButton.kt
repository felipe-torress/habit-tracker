package com.example.habittracker.ui.composables.buttons

import androidx.annotation.DrawableRes
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.habittracker.R
import com.example.habittracker.ui.screens.habits.model.ColorUI
import com.example.habittracker.ui.theme.HabitTrackerColors
import com.example.habittracker.ui.utils.previews.MockConstants
import com.example.habittracker.ui.utils.testTags.TestTagState

@Composable
fun ColorfulButton(
    onClick: () -> Unit,
    color: ColorUI,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    testTagState: TestTagState,
    @DrawableRes iconResId: Int? = null,
) {
    val (containerColor, contentColor) = when (color) {
        ColorUI.BLUE -> HabitTrackerColors.blue900 to HabitTrackerColors.blue50
        ColorUI.GREEN -> HabitTrackerColors.green900 to HabitTrackerColors.green50
        ColorUI.PURPLE -> HabitTrackerColors.purple900 to HabitTrackerColors.purple50
    }

    BasicButton(
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = HabitTrackerColors.darkGrey50,
            disabledContentColor = HabitTrackerColors.darkGrey300,
        ),
        onClick = onClick,
        text = text,
        modifier = modifier,
        enabled = enabled,
        testTagState = testTagState,
        iconResId = iconResId,
    )
}


//region --- Preview ---
private data class ColorfulButtonPreviewData(
    val color: ColorUI = ColorUI.GREEN,
    val text: String = MockConstants.add_habit_button_title,
    val iconResId: Int? = null,
    val enabled: Boolean = true,
)

private class ColorfulButtonPreviewParameterProvider : PreviewParameterProvider<ColorfulButtonPreviewData> {
    override val values = sequenceOf(
        // Button with no icon - enabled - GREEN
        ColorfulButtonPreviewData(),
        // Button with icon - enabled - GREEN
        ColorfulButtonPreviewData(iconResId = R.drawable.ic_habits_24dp),
        // Button with no icon - disabled - GREEN
        ColorfulButtonPreviewData(enabled = false),
        // Button with icon - disabled - GREEN
        ColorfulButtonPreviewData(iconResId = R.drawable.ic_habits_24dp, enabled = false),

        // Button with no icon - enabled - BLUE
        ColorfulButtonPreviewData(color = ColorUI.BLUE),
        // Button with icon - enabled - BLUE
        ColorfulButtonPreviewData(color = ColorUI.BLUE, iconResId = R.drawable.ic_habits_24dp),
        // Button with no icon - disabled - BLUE
        ColorfulButtonPreviewData(color = ColorUI.BLUE, enabled = false),
        // Button with icon - disabled - BLUE
        ColorfulButtonPreviewData(color = ColorUI.BLUE, iconResId = R.drawable.ic_habits_24dp, enabled = false),

        // Button with no icon - enabled - PURPLE
        ColorfulButtonPreviewData(color = ColorUI.PURPLE),
        // Button with icon - enabled - PURPLE
        ColorfulButtonPreviewData(color = ColorUI.PURPLE, iconResId = R.drawable.ic_habits_24dp),
        // Button with no icon - disabled - PURPLE
        ColorfulButtonPreviewData(color = ColorUI.PURPLE, enabled = false),
        // Button with icon - disabled - PURPLE
        ColorfulButtonPreviewData(color = ColorUI.PURPLE, iconResId = R.drawable.ic_habits_24dp, enabled = false),
    )
}

@Preview
@Composable
private fun ColorfulButtonPreview(
    @PreviewParameter(ColorfulButtonPreviewParameterProvider::class) previewData: ColorfulButtonPreviewData,
) {
    ColorfulButton(
        onClick = {},
        text = previewData.text,
        color = previewData.color,
        iconResId = previewData.iconResId,
        enabled = previewData.enabled,
        testTagState = TestTagState(""),
    )
}
//endregion
