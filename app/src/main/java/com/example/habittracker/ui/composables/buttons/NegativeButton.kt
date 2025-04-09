package com.example.habittracker.ui.composables.buttons

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.habittracker.R
import com.example.habittracker.ui.theme.HabitTrackerColors
import com.example.habittracker.ui.utils.testTags.TestTagState

@Composable
fun NegativeButton(
    onClick: () -> Unit,
    title: String,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
    iconResId: Int? = null,
) {
    BasicButton(
        text = title,
        iconResId = iconResId,
        colors = ButtonDefaults.buttonColors(
            containerColor = HabitTrackerColors.red50,
            contentColor = HabitTrackerColors.red500,
        ),
        onClick = onClick,
        testTagState = testTagState,
        modifier = modifier,
    )
}

// //region --- Preview ---
private data class NegativeButtonPreviewData(val iconResId: Int? = null)

private class NegativeButtonPreviewProvider : PreviewParameterProvider<NegativeButtonPreviewData> {
    override val values = sequenceOf(
        // No Icon
        NegativeButtonPreviewData(),
        // With Icon
        NegativeButtonPreviewData(iconResId = R.drawable.ic_trash_24dp),
    )
}

@Preview
@Composable
private fun NegativeButtonPreview(
    @PreviewParameter(NegativeButtonPreviewProvider::class) previewData: NegativeButtonPreviewData,
) {
    NegativeButton(
        onClick = {},
        title = stringResource(R.string.habit_details_screen_delete_button_title),
        iconResId = previewData.iconResId,
        testTagState = TestTagState(
            origin = "Preview",
            action = "NegativeButton",
            section = "Test",
        ),
    )
}
//endregion
