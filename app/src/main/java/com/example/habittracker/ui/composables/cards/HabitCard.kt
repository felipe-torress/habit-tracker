package com.example.habittracker.ui.composables.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.habittracker.R
import com.example.habittracker.ui.composables.buttons.ColorfulButton
import com.example.habittracker.ui.composables.progress.ProgressIndicator
import com.example.habittracker.ui.screens.habits.model.ColorUI
import com.example.habittracker.ui.screens.habits.model.HabitTaskUIData
import com.example.habittracker.ui.theme.HabitTrackerColors
import com.example.habittracker.ui.theme.HabitTrackerTypography
import com.example.habittracker.ui.utils.compose.rememberInteractionsSource
import com.example.habittracker.ui.utils.datetime.toDaysOfWeekText
import com.example.habittracker.ui.utils.datetime.toLocalizedTime
import com.example.habittracker.ui.utils.previews.MockConstants
import com.example.habittracker.ui.utils.previews.Mocks
import com.example.habittracker.ui.utils.testTags.TestTagState
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun HabitCard(
    title: String,
    daysOfWeek: List<DayOfWeek>,
    tasks: List<HabitTaskUIData>,
    color: ColorUI,
    onHeaderClick: () -> Unit,
    onAddTaskClick: () -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    val localTestTag = "${testTagState.origin}HabitCard${testTagState.index}"
    val localTestTagState = TestTagState(localTestTag)

    val colors: HabitCardColors = remember { getCardColors(color) }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(colors.background)
            .testTag(localTestTag),
    ) {
        Header(
            title = title,
            daysOfWeek = daysOfWeek,
            onClick = onHeaderClick,
            colors = colors,
            testTagState = localTestTagState,
        )
        Content(
            tasks = tasks,
            color = color,
            onAddTaskClick = onAddTaskClick,
            testTagState = localTestTagState,
        )
    }
}

@Composable
private fun Header(
    title: String,
    daysOfWeek: List<DayOfWeek>,
    onClick: () -> Unit,
    colors: HabitCardColors,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .heightIn(78.dp)
            .background(colors.header)
            .clickable(
                interactionSource = rememberInteractionsSource(),
                indication = ripple(color = colors.ripple),
            ) { onClick() }
            .padding(16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = HabitTrackerTypography.subtitle1,
                color = HabitTrackerColors.textColor,
                modifier = Modifier.testTag("${testTagState.origin}Title"),
            )

            if (daysOfWeek.isNotEmpty()) {
                RepeatInfo(
                    daysOfWeek = daysOfWeek,
                    colors = colors,
                    testTagState = testTagState,
                )
            }
        }

        Icon(
            painter = painterResource(R.drawable.ic_maximize_24dp),
            contentDescription = null,
            tint = colors.icon,
        )
    }
}

@Composable
private fun RepeatInfo(
    daysOfWeek: List<DayOfWeek>,
    colors: HabitCardColors,
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
            tint = colors.icon,
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
private fun Content(
    tasks: List<HabitTaskUIData>,
    color: ColorUI,
    testTagState: TestTagState,
    onAddTaskClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val iconColor = remember { getCardColors(color).icon }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        if (tasks.isNotEmpty()) {
            TasksSection(
                tasks = tasks,
                iconColor = iconColor,
                color = color,
                testTagState = testTagState
            )
        } else {
            AddTaskSection(
                color = color,
                onAddTaskClick = onAddTaskClick,
                testTagState = testTagState,
            )
        }
    }
}

@Composable
private fun TasksSection(
    tasks: List<HabitTaskUIData>,
    iconColor: Color,
    color: ColorUI,
    testTagState: TestTagState
) {
    Text(
        text = pluralStringResource(R.plurals.habit_card_tasks_header_title, tasks.size),
        style = HabitTrackerTypography.bodyLarge,
        fontWeight = FontWeight.Bold,
        color = iconColor,
    )

    tasks.forEachIndexed { index, task ->
        TaskItem(
            name = task.name,
            time = task.time,
            maximumProgress = task.requiredWeeklyCompletions,
            currentProgress = task.currentWeeklyCompletions,
            color = color,
            testTagState = testTagState.index(index),
        )
    }
}

@Composable
fun TaskItem(
    name: String,
    maximumProgress: Int,
    currentProgress: Int,
    time: LocalTime,
    color: ColorUI,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .testTag("${testTagState.origin}Task${testTagState.index}"),
    ) {
        Column(modifier = Modifier.weight(1f)) {
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

        ProgressIndicator(
            maximum = maximumProgress,
            current = currentProgress,
            color = color,
            testTagState = testTagState,
        )
    }
}

@Composable
private fun AddTaskSection(
    color: ColorUI,
    onAddTaskClick: () -> Unit,
    testTagState: TestTagState,
) {
    val (textColor, iconColor) = when (color) {
        ColorUI.GREEN -> HabitTrackerColors.green900 to HabitTrackerColors.green800
        ColorUI.BLUE -> HabitTrackerColors.blue900 to HabitTrackerColors.blue800
        ColorUI.PURPLE -> HabitTrackerColors.purple900 to HabitTrackerColors.purple800
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.ic_alert_16dp),
                contentDescription = null,
                colorFilter = ColorFilter.tint(iconColor),
                modifier = Modifier.size(16.dp)
            )

            Text(
                text = stringResource(R.string.habit_list_screen_no_tasks_for_habit_warning_message),
                style = HabitTrackerTypography.bodySmall,
                color = textColor,
            )
        }

        ColorfulButton(
            onClick = onAddTaskClick,
            color = color,
            iconResId = R.drawable.ic_progress_24dp,
            text = stringResource(R.string.add_habit_screen_add_task_button_title),
            testTagState = testTagState.action("AddTask"),
        )
    }
}

//region --- Helper Methods ---
private data class HabitCardColors(
    val background: Color,
    val header: Color,
    val ripple: Color,
    val icon: Color,
    val sectionTitle: Color,
)

private fun getCardColors(color: ColorUI): HabitCardColors {
    return when (color) {
        ColorUI.GREEN -> HabitCardColors(
            background = HabitTrackerColors.green50,
            header = HabitTrackerColors.softGreen,
            ripple = HabitTrackerColors.green500,
            icon = HabitTrackerColors.green700,
            sectionTitle = HabitTrackerColors.green700,
        )

        ColorUI.BLUE -> HabitCardColors(
            background = HabitTrackerColors.blue50,
            header = HabitTrackerColors.softBlue,
            ripple = HabitTrackerColors.blue500,
            icon = HabitTrackerColors.blue700,
            sectionTitle = HabitTrackerColors.blue700,
        )

        ColorUI.PURPLE -> HabitCardColors(
            background = HabitTrackerColors.purple50,
            header = HabitTrackerColors.softPurple,
            ripple = HabitTrackerColors.purple500,
            icon = HabitTrackerColors.purple700,
            sectionTitle = HabitTrackerColors.purple700,
        )
    }
}
//endregion

//region --- Previews ---
private data class HabitCardPreviewData(
    val title: String = MockConstants.habit_title_1,
    val daysOfWeek: List<DayOfWeek> = MockConstants.daysOfWeek_1,
    val tasks: List<HabitTaskUIData> = listOf(
        Mocks.habitTaskUIData_1,
        Mocks.habitTaskUIData_2,
        Mocks.habitTaskUIData_3,
    ),
    val color: ColorUI = ColorUI.BLUE,
)

private class HabitCardPreviewParameterProvider : PreviewParameterProvider<HabitCardPreviewData> {
    override val values = sequenceOf(
        // Blue
        HabitCardPreviewData(),
        // Purple
        HabitCardPreviewData(color = ColorUI.PURPLE),
        // Green
        HabitCardPreviewData(color = ColorUI.GREEN),
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
        color = previewData.color,
        onHeaderClick = {},
        onAddTaskClick = {},
        testTagState = TestTagState(""),
    )
}
//endregion
