package com.example.habittrackernew.ui.composables.buttons

import androidx.annotation.DrawableRes
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.utils.previews.MockConstants

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    @DrawableRes iconResId: Int? = null,
) {
    BasicButton(
        onClick = onClick,
        text = text,
        iconResId = iconResId,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = HabitTrackerColors.green700,
            contentColor = HabitTrackerColors.green50,
            disabledContainerColor = HabitTrackerColors.green700, // TODO: Change to disabled color
            disabledContentColor = HabitTrackerColors.green50, // TODO: Change to disabled color
        ),
        modifier = modifier,
    )
}

//region --- Preview ---
private data class PrimaryButtonPreviewData(
    val text: String = MockConstants.add_habit_button_title,
    val iconResId: Int? = null,
    val enabled: Boolean = true,
)

private class PrimaryButtonPreviewParameterProvider : PreviewParameterProvider<PrimaryButtonPreviewData> {
    override val values = sequenceOf(
        // Button with no icon - enabled
        PrimaryButtonPreviewData(),

        // Button with icon - enabled
        PrimaryButtonPreviewData(iconResId = R.drawable.ic_habits_24dp)
    )
}

@Preview
@Composable
private fun PrimaryButtonPreview(@PreviewParameter(PrimaryButtonPreviewParameterProvider::class) previewData: PrimaryButtonPreviewData) {
    PrimaryButton(
        onClick = {},
        text = previewData.text,
        iconResId = previewData.iconResId,
        enabled = previewData.enabled
    )
}
//endregion
