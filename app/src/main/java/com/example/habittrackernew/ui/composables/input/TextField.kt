package com.example.habittrackernew.ui.composables.input

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.theme.HabitTrackerTypography
import com.example.habittrackernew.ui.utils.previews.DefaultBackgroundPreview
import com.example.habittrackernew.ui.utils.testTags.TestTagState

@Composable
fun HabitTrackerTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    label: String,
    @DrawableRes labelIconResId: Int,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    val testTag = "${testTagState.origin}${testTagState.type}TextField"
    val innerTestTagState = TestTagState(testTag)

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = modifier.testTag("${testTag}Container"),
    ) {
        Label(
            label = label,
            iconResId = labelIconResId,
            testTagState = innerTestTagState,
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(16.dp),
            textStyle = HabitTrackerTypography.bodyLarge,
            placeholder = { Placeholder(placeholder = placeholder, testTagState = innerTestTagState) },
            colors = TextFieldDefaults.colors(
                // background colors
                focusedContainerColor = HabitTrackerColors.softGreen,
                unfocusedContainerColor = HabitTrackerColors.softGreen,
                disabledContainerColor = HabitTrackerColors.softGreen,
                // text color
                focusedTextColor = HabitTrackerColors.green900,
                unfocusedTextColor = HabitTrackerColors.green900,
                disabledTextColor = HabitTrackerColors.green900,
                // cursor color
                cursorColor = HabitTrackerColors.green900,
                // label color
                focusedLabelColor = HabitTrackerColors.green900,
                unfocusedLabelColor = HabitTrackerColors.green900,
                disabledLabelColor = HabitTrackerColors.green900,
                // indicator color
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .testTag(testTag),
        )
    }
}

@Composable
private fun Placeholder(
    placeholder: String,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Text(
        text = placeholder,
        style = HabitTrackerTypography.bodyLarge,
        color = HabitTrackerColors.green600,
        modifier = modifier.testTag("${testTagState.origin}Placeholder"),
    )
}

@Composable
private fun Label(
    label: String,
    @DrawableRes iconResId: Int,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.semantics(mergeDescendants = true) {},
    ) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = null,
            tint = HabitTrackerColors.green700,
            modifier = Modifier
                .size(16.dp)
                .testTag("${testTagState.origin}LabelIcon"),
        )

        Text(
            text = label,
            style = HabitTrackerTypography.bodyLarge,
            color = HabitTrackerColors.green700,
            modifier = Modifier.testTag("${testTagState.origin}LabelText"),
        )
    }
}

//region MARK: --- Preview ---
private data class HabitTrackerTextFieldPreviewData(
    val value: String = "",
    val placeholder: String = "Qual o nome do seu hábito?",
    val label: String = "Hábito",
    val iconResId: Int = R.drawable.ic_habits_16dp,
)

private class HabitTrackerTextFieldPreviewProvider : PreviewParameterProvider<HabitTrackerTextFieldPreviewData> {
    override val values = sequenceOf(
        HabitTrackerTextFieldPreviewData(),
        HabitTrackerTextFieldPreviewData(value = "Hábito 1"),
    )
}

@DefaultBackgroundPreview
@Composable
private fun HabitTrackerTextFieldPreview(
    @PreviewParameter(HabitTrackerTextFieldPreviewProvider::class) previewData: HabitTrackerTextFieldPreviewData,
) {
    HabitTrackerTextField(
        value = previewData.value,
        placeholder = previewData.placeholder,
        label = previewData.label,
        labelIconResId = previewData.iconResId,
        onValueChange = {},
        testTagState = TestTagState("HabitTrackerTextFieldPreview"),
    )
}
//endregion
