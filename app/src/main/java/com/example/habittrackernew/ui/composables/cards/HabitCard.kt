package com.example.habittrackernew.ui.composables.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.screens.habits.model.HabitTaskUIData
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.theme.HabitTrackerTypography
import com.example.habittrackernew.ui.utils.compose.rememberInteractionsSource
import com.example.habittrackernew.ui.utils.previews.MockConstants
import com.example.habittrackernew.ui.utils.previews.Mocks
import com.example.habittrackernew.ui.utils.testTags.TestTagState
import com.example.habittrackernew.ui.utils.toDaysOfWeekText
import com.example.habittrackernew.ui.utils.toLocalizedTime
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun HabitCard(
    title: String,
    daysOfWeek: List<DayOfWeek>,
    tasks: List<HabitTaskUIData>,
    onClick: () -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    val localTestTag = "${testTagState.origin}HabitCard${testTagState.index}"
    val localTestTagState = TestTagState(localTestTag)

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(HabitTrackerColors.softBlue)
            .testTag(localTestTag),
    ) {
        Header(
            title = title,
            daysOfWeek = daysOfWeek,
            onClick = onClick,
            testTagState = localTestTagState,
        )
        Content(
            tasks = tasks,
            testTagState = localTestTagState,
        )
    }
}

@Composable
private fun Header(
    title: String,
    daysOfWeek: List<DayOfWeek>,
    onClick: () -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(HabitTrackerColors.softBlue2)
            .clickable(
                interactionSource = rememberInteractionsSource(),
                indication = rememberRipple(color = HabitTrackerColors.blue500),
            ) { onClick() }
            .padding(16.dp),
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = HabitTrackerTypography.subtitle1,
                color = HabitTrackerColors.textColor,
                modifier = Modifier.testTag("${testTagState.origin}Title"),
            )

            RepeatInfo(daysOfWeek, testTagState)
        }

        Icon(
            painter = painterResource(R.drawable.ic_maximize_24dp),
            contentDescription = null,
            tint = HabitTrackerColors.blue700,
        )
    }
}

@Composable
fun RepeatInfo(
    daysOfWeek: List<DayOfWeek>,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_repeat_16dp),
            contentDescription = null,
            tint = HabitTrackerColors.blue700,
        )
        Text(
            text = daysOfWeek.toDaysOfWeekText(),
            style = HabitTrackerTypography.bodySmall,
            fontWeight = FontWeight.Normal,
            color = HabitTrackerColors.textColor,
            modifier = Modifier.testTag("${testTagState.origin}RepeatInformation"),
        )
    }
}

@Composable
fun Content(
    tasks: List<HabitTaskUIData>,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(R.string.habit_card_tasks_header_title),
            style = HabitTrackerTypography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = HabitTrackerColors.blue500,
        )

        tasks.forEachIndexed { index, task ->
            TaskItem(
                name = task.name,
                time = task.time,
                testTagState = testTagState.index(index),
            )
        }
    }
}

@Composable
fun TaskItem(
    name: String,
    time: LocalTime,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .testTag("${testTagState.origin}Task${testTagState.index}"),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = name,
                style = HabitTrackerTypography.bodySmall,
                color = HabitTrackerColors.textColor,
                modifier = Modifier.testTag("${testTagState.origin}Task${testTagState.index}Name"),
            )
            Text(
                text = time.toLocalizedTime(),
                style = HabitTrackerTypography.caption,
                fontWeight = FontWeight.Normal,
                color = HabitTrackerColors.textColor,
                modifier = Modifier.testTag("${testTagState.origin}Task${testTagState.index}Time"),
            )
        }

        // TODO: Add Progress Indicator (will be added on future PR)
    }
}

//region --- Previews ---
private data class HabitCardPreviewData(
    val title: String = MockConstants.habit_title_1,
    val daysOfWeek: List<DayOfWeek> = MockConstants.daysOfWeek_1,
    val tasks: List<HabitTaskUIData> = listOf(
        Mocks.habitTaskUIData_1,
        Mocks.habitTaskUIData_2,
        Mocks.habitTaskUIData_3,
    ),
)

private class HabitCardPreviewParameterProvider : PreviewParameterProvider<HabitCardPreviewData> {
    override val values = sequenceOf(
        HabitCardPreviewData(),
    )
}

@Preview
@Composable
private fun HabitCardPreview(
    @PreviewParameter(HabitCardPreviewParameterProvider::class) previewData: HabitCardPreviewData,
) {
    HabitCard(
        title = previewData.title,
        daysOfWeek = previewData.daysOfWeek,
        tasks = previewData.tasks,
        onClick = {},
        testTagState = TestTagState(""),
    )
}
//endregion
