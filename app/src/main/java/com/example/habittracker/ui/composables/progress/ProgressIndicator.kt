package com.example.habittracker.ui.composables.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.habittracker.R
import com.example.habittracker.ui.screens.habits.model.ColorUI
import com.example.habittracker.ui.theme.HabitTrackerColors
import com.example.habittracker.ui.theme.HabitTrackerTypography
import com.example.habittracker.ui.utils.testTags.TestTagState

@Composable
fun ProgressIndicator(
    current: Int,
    maximum: Int,
    color: ColorUI,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    val testTag = "${testTagState.origin}ProgressIndicator${testTagState.index}"

    val textColor = remember {
        when (color) {
            ColorUI.BLUE -> HabitTrackerColors.blue700
            ColorUI.PURPLE -> HabitTrackerColors.purple700
            ColorUI.GREEN -> HabitTrackerColors.green700
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.testTag(testTag),
    ) {
        Text(
            text = stringResource(R.string.progress_indicator_text, current, maximum),
            style = HabitTrackerTypography.caption,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.testTag("${testTag}Text"),
        )

        ProgressBar(
            current = current,
            maximum = maximum,
            color = color,
        )
    }
}

@Composable
private fun ProgressBar(
    current: Int,
    maximum: Int,
    color: ColorUI,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(8.dp)

    val barColor = remember {
        when (color) {
            ColorUI.BLUE -> HabitTrackerColors.blue500
            ColorUI.PURPLE -> HabitTrackerColors.purple500
            ColorUI.GREEN -> HabitTrackerColors.green500
        }
    }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .height(8.dp)
            .width(80.dp)
            .background(color = HabitTrackerColors.progressIndicatorBackground, shape = shape),
    ) {
        val width = (current.toFloat() / maximum.toFloat()) * 80.dp

        Box(
            modifier = Modifier
                .shadow(elevation = 24.dp, shape = shape, spotColor = barColor, ambientColor = barColor)
                .height(8.dp)
                .width(width)
                .background(color = barColor, shape = shape),
        )
    }
}

//region --- Preview ---
private data class ProgressIndicatorPreviewData(
    val maximum: Int = 10,
    val current: Int = 5,
    val color: ColorUI = ColorUI.BLUE,
)

private class ProgressIndicatorPreviewParameterProvider : PreviewParameterProvider<ProgressIndicatorPreviewData> {
    override val values = sequenceOf(
        // 5/10 - Blue
        ProgressIndicatorPreviewData(),
        // 10/10 - Blue
        ProgressIndicatorPreviewData(current = 10),
        // 0/10 - Blue
        ProgressIndicatorPreviewData(current = 0),
        // 5/10 - Purple
        ProgressIndicatorPreviewData(color = ColorUI.PURPLE),
        // 10/10 - Purple
        ProgressIndicatorPreviewData(color = ColorUI.PURPLE, current = 10),
        // 0/10 - Purple
        ProgressIndicatorPreviewData(color = ColorUI.PURPLE, current = 0),
        // 5/10 - Green
        ProgressIndicatorPreviewData(color = ColorUI.GREEN),
        // 10/10 - Green
        ProgressIndicatorPreviewData(color = ColorUI.GREEN, current = 10),
        // 0/10 - Green
        ProgressIndicatorPreviewData(color = ColorUI.GREEN, current = 0),
    )
}

@Preview
@Composable
private fun ProgressIndicatorPreview(
    @PreviewParameter(ProgressIndicatorPreviewParameterProvider::class) previewData: ProgressIndicatorPreviewData,
) {
    Box(
        modifier = Modifier
            .background(HabitTrackerColors.backgroundColor)
            .padding(8.dp),
    ) {
        ProgressIndicator(
            current = previewData.current,
            maximum = previewData.maximum,
            color = previewData.color,
            testTagState = TestTagState("ProgressIndicator"),
        )
    }
}
//endregion
