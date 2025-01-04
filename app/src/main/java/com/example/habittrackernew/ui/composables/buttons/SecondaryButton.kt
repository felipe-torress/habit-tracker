package com.example.habittrackernew.ui.composables.buttons

import androidx.annotation.DrawableRes
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.utils.previews.DefaultBackgroundPreview
import com.example.habittrackernew.ui.utils.previews.MockConstants
import com.example.habittrackernew.ui.utils.testTags.TestTagState

@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    testTagState: TestTagState,
    @DrawableRes iconResId: Int? = null,
) {
    BasicButton(
        onClick = onClick,
        text = text,
        iconResId = iconResId,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = HabitTrackerColors.green100,
            contentColor = HabitTrackerColors.green900,
            disabledContainerColor = HabitTrackerColors.darkGrey50,
            disabledContentColor = HabitTrackerColors.darkGrey100,
        ),
        testTagState = testTagState,
        modifier = modifier,
    )
}

//region --- Preview ---
private data class SecondaryButtonPreviewData(
    val text: String = MockConstants.add_habit_button_title,
    val iconResId: Int? = null,
    val enabled: Boolean = true,
)

private class SecondaryButtonPreviewParameterProvider : PreviewParameterProvider<SecondaryButtonPreviewData> {
    override val values = sequenceOf(
        // Button with no icon - enabled
        SecondaryButtonPreviewData(),
        // Button with icon - enabled
        SecondaryButtonPreviewData(iconResId = R.drawable.ic_habits_24dp),
    )
}

@DefaultBackgroundPreview
@Composable
private fun SecondaryButtonPreview(
    @PreviewParameter(SecondaryButtonPreviewParameterProvider::class) previewData: SecondaryButtonPreviewData,
) {
    SecondaryButton(
        onClick = {},
        text = previewData.text,
        iconResId = previewData.iconResId,
        enabled = previewData.enabled,
        testTagState = TestTagState("")
    )
}
//endregion
