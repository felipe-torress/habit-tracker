package com.example.habittrackernew.ui.composables.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.theme.HabitTrackerTypography
import com.example.habittrackernew.ui.utils.previews.MockConstants
import com.example.habittrackernew.ui.utils.testTags.TestTagState

@Composable
fun BasicButton(
    onClick: () -> Unit,
    text: String,
    colors: ButtonColors,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    testTagState: TestTagState,
    @DrawableRes iconResId: Int? = null,
) {
    Button(
        shape = RoundedCornerShape(16.dp),
        colors = colors,
        onClick = onClick,
        enabled = enabled,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .testTag("${testTagState.origin}${testTagState.action}Button")
    ) {
        iconResId?.let { icon ->
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(24.dp),
            )
        }

        Text(
            text = text,
            style = HabitTrackerTypography.button,
        )
    }
}

//region --- Preview ---
private data class BasicButtonPreviewData(
    val text: String = MockConstants.add_habit_button_title,
    val iconResId: Int? = null,
    val colors: ButtonColors = ButtonColors(
        containerColor = HabitTrackerColors.green700,
        contentColor = HabitTrackerColors.green50,
        disabledContainerColor = HabitTrackerColors.green700,
        disabledContentColor = HabitTrackerColors.green50,
    ),
    val enabled: Boolean = true,
)

private class BasicButtonPreviewParameterProvider : PreviewParameterProvider<BasicButtonPreviewData> {
    override val values = sequenceOf(
        // Button with no icon - enabled
        BasicButtonPreviewData(),
        // Button with icon - enabled
        BasicButtonPreviewData(iconResId = R.drawable.ic_habits_24dp),
    )
}

@Preview
@Composable
private fun BasicButtonPreview(
    @PreviewParameter(BasicButtonPreviewParameterProvider::class) previewData: BasicButtonPreviewData,
) {
    BasicButton(
        onClick = {},
        text = previewData.text,
        colors = previewData.colors,
        iconResId = previewData.iconResId,
        enabled = previewData.enabled,
        testTagState = TestTagState(""),
    )
}
//endregion
