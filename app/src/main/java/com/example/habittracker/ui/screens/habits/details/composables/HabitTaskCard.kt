package com.example.habittracker.ui.screens.habits.details.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.habittracker.R
import com.example.habittracker.ui.composables.buttons.iconbuttons.EditButton
import com.example.habittracker.ui.composables.progress.ProgressIndicator
import com.example.habittracker.ui.screens.habits.model.ColorUI
import com.example.habittracker.ui.screens.habits.model.HabitTaskUIData
import com.example.habittracker.ui.theme.HabitTrackerColors
import com.example.habittracker.ui.theme.HabitTrackerTypography
import com.example.habittracker.ui.utils.datetime.toDaysOfWeekText
import com.example.habittracker.ui.utils.datetime.toLocalizedTime
import com.example.habittracker.ui.utils.previews.Mocks
import com.example.habittracker.ui.utils.testTags.TestTagState
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun HabitTaskCard(
    task: HabitTaskUIData,
    color: ColorUI,
    onEditClick: (taskId: String) -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    val testTag = "${testTagState.origin}HabitTaskCard${testTagState.index}"
    val innerTestTag = TestTagState(testTag)

    val backgroundColor = remember {
        when (color) {
            ColorUI.BLUE -> HabitTrackerColors.blue50
            ColorUI.GREEN -> HabitTrackerColors.green50
            ColorUI.PURPLE -> HabitTrackerColors.purple50
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("${testTagState.origin}HabitTaskCard${testTagState.index}"),
    ) {
        Header(
            name = task.name,
            onEditClick = { onEditClick(task.id) },
            color = color,
            testTagState = innerTestTag,
        )

        Info(
            daysOfWeek = task.daysOfWeek,
            time = task.time,
            maximumProgress = task.requiredWeeklyCompletions,
            currentProgress = task.currentWeeklyCompletions,
            color = color,
            testTagState = innerTestTag,
        )
    }
}

@Composable
private fun Header(
    name: String,
    onEditClick: () -> Unit,
    color: ColorUI,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = name,
            style = HabitTrackerTypography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = HabitTrackerColors.textColor,
            modifier = Modifier
                .weight(1f)
                .testTag("${testTagState.origin}Title"),
        )

        EditButton(
            onClick = onEditClick,
            color = color,
            modifier = Modifier.testTag("${testTagState.origin}EditButton"),
            testTagState = testTagState,
        )
    }
}

@Composable
private fun Info(
    daysOfWeek: List<DayOfWeek>,
    time: LocalTime,
    maximumProgress: Int,
    currentProgress: Int,
    color: ColorUI,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        DateTimeInfo(
            daysOfWeek = daysOfWeek,
            time = time,
            color = color,
            testTagState = testTagState,
            modifier = Modifier.weight(1f),
        )

        ProgressInfo(
            maximumProgress = maximumProgress,
            currentProgress = currentProgress,
            color = color,
            testTagState = testTagState,
        )
    }
}

@Composable
private fun DateTimeInfo(
    daysOfWeek: List<DayOfWeek>,
    time: LocalTime,
    color: ColorUI,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier,
    ) {
        DateTimeInfoItem(
            text = time.toLocalizedTime(),
            iconResId = R.drawable.ic_clock_16dp,
            color = color,
            testTagState = testTagState.type("Time"),
        )

        DateTimeInfoItem(
            text = daysOfWeek.toDaysOfWeekText(),
            iconResId = R.drawable.ic_repeat_16dp,
            color = color,
            testTagState = testTagState.type("DaysOfWeek"),
        )
    }
}

@Composable
private fun DateTimeInfoItem(
    text: String,
    @DrawableRes iconResId: Int,
    color: ColorUI,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    val iconColor = when (color) {
        ColorUI.BLUE -> HabitTrackerColors.blue700
        ColorUI.GREEN -> HabitTrackerColors.green700
        ColorUI.PURPLE -> HabitTrackerColors.purple700
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier
                .size(16.dp)
                .testTag("${testTagState.origin}${testTagState.type}InfoIcon"),
        )

        Text(
            text = text,
            style = HabitTrackerTypography.bodySmall,
            fontWeight = FontWeight.Normal,
            color = HabitTrackerColors.textColor,
            modifier = Modifier.testTag("${testTagState.origin}${testTagState.type}InfoText"),
        )
    }
}

@Composable
private fun ProgressInfo(
    maximumProgress: Int,
    currentProgress: Int,
    color: ColorUI,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    val textColor = when (color) {
        ColorUI.BLUE -> HabitTrackerColors.blue800
        ColorUI.PURPLE -> HabitTrackerColors.purple800
        ColorUI.GREEN -> HabitTrackerColors.green800
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        ProgressIndicator(
            current = currentProgress,
            maximum = maximumProgress,
            color = color,
            testTagState = testTagState,
        )

        Text(
            text = stringResource(R.string.habit_details_screen_task_card_weekly_goal).uppercase(),
            style = HabitTrackerTypography.caption,
            fontWeight = FontWeight.Bold,
            color = textColor,
        )
    }
}

//region --- Preview ---
private data class HabitTaskCardPreviewData(
    val task: HabitTaskUIData = Mocks.habitTaskUIData_1,
    val color: ColorUI = ColorUI.BLUE,
)

private class HabitTaskCardPreviewParameterProvider : PreviewParameterProvider<HabitTaskCardPreviewData> {
    override val values = sequenceOf(
        // Blue
        HabitTaskCardPreviewData(),
        // Purple
        HabitTaskCardPreviewData(color = ColorUI.PURPLE),
        // Green
        HabitTaskCardPreviewData(color = ColorUI.GREEN),
    )
}

@Preview
@Composable
private fun HabitTaskCardPreview(
    @PreviewParameter(HabitTaskCardPreviewParameterProvider::class) previewData: HabitTaskCardPreviewData,
) {
    HabitTaskCard(
        task = previewData.task,
        color = previewData.color,
        onEditClick = {},
        testTagState = TestTagState("HabitTaskCardPreview"),
    )
}
//endregion
