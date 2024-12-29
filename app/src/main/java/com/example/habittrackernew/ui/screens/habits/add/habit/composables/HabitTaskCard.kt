package com.example.habittrackernew.ui.screens.habits.add.habit.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
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
import com.example.habittrackernew.ui.utils.datetime.toDaysOfWeekText
import com.example.habittrackernew.ui.utils.datetime.toLocalizedTime
import com.example.habittrackernew.ui.utils.previews.Mocks
import com.example.habittrackernew.ui.utils.testTags.TestTagState
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun HabitTaskCard(
    task: HabitTaskUIData,
    onEditClick: () -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier
) {
    val testTag = "${testTagState.origin}${testTagState.section}HabitTaskCard${testTagState.index}"

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(HabitTrackerColors.softGreen)
            .padding(16.dp)
            .testTag(testTag)
    ) {
        Header(
            name = task.name,
            onEditClick = onEditClick,
            testTagState = TestTagState(testTag)
        )

        DateTimeInfo(
            daysOfWeek = task.daysOfWeek,
            time = task.time,
            testTagState = TestTagState(testTag)
        )
    }
}

@Composable
fun Header(
    name: String,
    onEditClick: () -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier
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
                .testTag("${testTagState.origin}Name"),
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_pencil_24dp),
            contentDescription = null,
            tint = HabitTrackerColors.green700,
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    interactionSource = rememberInteractionsSource(),
                    indication = ripple(
                        color = HabitTrackerColors.green500,
                        bounded = false,
                        radius = 24.dp
                    )
                ) { onEditClick() }
                .testTag("${testTagState.origin}EditIconButton")
        )
    }
}

@Composable
private fun DateTimeInfo(
    daysOfWeek: List<DayOfWeek>,
    time: LocalTime,
    testTagState: TestTagState,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        DaysOfWeek(
            daysOfWeek = daysOfWeek,
            modifier = Modifier.testTag("${testTagState.origin}DaysOfWeek")
        )

        Time(
            time = time,
            modifier = Modifier.testTag("${testTagState.origin}Time")
        )
    }
}

@Composable
private fun DaysOfWeek(
    daysOfWeek: List<DayOfWeek>,
    modifier: Modifier = Modifier
) {
    DateTimeInfoItem(
        info = daysOfWeek.toDaysOfWeekText(),
        iconResId = R.drawable.ic_repeat_16dp,
        modifier = modifier
    )
}

@Composable
private fun Time(
    time: LocalTime,
    modifier: Modifier = Modifier
) {
    DateTimeInfoItem(
        info = time.toLocalizedTime(),
        iconResId = R.drawable.ic_clock_16dp,
        modifier = modifier
    )
}

@Composable
private fun DateTimeInfoItem(
    info: String,
    @DrawableRes iconResId: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = null,
            tint = HabitTrackerColors.green700,
            modifier = Modifier.size(16.dp)
        )

        Text(
            text = info,
            style = HabitTrackerTypography.bodySmall,
            fontWeight = FontWeight.Normal,
            color = HabitTrackerColors.textColor
        )
    }
}

//region --- Preview ---
private data class HabitTaskCardPreviewData(
    val task: HabitTaskUIData = Mocks.habitTaskUIData_1
)

private class HabitTaskCardPreviewParameterProvider : PreviewParameterProvider<HabitTaskCardPreviewData> {
    override val values = sequenceOf(
        HabitTaskCardPreviewData(),
        HabitTaskCardPreviewData(task = Mocks.habitTaskUIData_2),
        HabitTaskCardPreviewData(task = Mocks.habitTaskUIData_3),
    )
}

@Preview
@Composable
private fun HabitTaskCardPreview(@PreviewParameter(HabitTaskCardPreviewParameterProvider::class) previewData: HabitTaskCardPreviewData) {
    HabitTaskCard(
        task = previewData.task,
        onEditClick = {},
        testTagState = TestTagState("HabitTaskCard")
    )
}
//endregion
