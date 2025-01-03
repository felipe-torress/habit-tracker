package com.example.habittrackernew.ui.composables.datetime

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.theme.HabitTrackerTypography
import com.example.habittrackernew.ui.utils.datetime.toDayOfWeekInitial
import java.time.DayOfWeek

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DayOfWeekPicker(
    selectedDaysOfWeek: List<DayOfWeek>,
    onDayOfWeekClick: (DayOfWeek) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        modifier = modifier.fillMaxWidth(),
    ) {
        DayOfWeek.entries.forEach { dayOfWeek ->
            DayOfWeekItem(
                dayOfWeek = dayOfWeek,
                isSelected = dayOfWeek in selectedDaysOfWeek,
                onClick = onDayOfWeekClick,
            )
        }
    }
}

@Composable
fun DayOfWeekItem(
    dayOfWeek: DayOfWeek,
    isSelected: Boolean,
    onClick: (DayOfWeek) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (backgroundColor, textColor) = if (isSelected) {
        HabitTrackerColors.green700 to HabitTrackerColors.green50
    } else {
        HabitTrackerColors.softGreen to HabitTrackerColors.textColor
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .size(36.dp)
            .background(backgroundColor)
            .clickable { onClick(dayOfWeek) }
            .padding(8.dp),
    ) {
        Text(
            text = dayOfWeek.toDayOfWeekInitial(),
            style = HabitTrackerTypography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = textColor,
        )
    }
}

//region --- Preview ---
private data class DayOfWeekPickerPreviewData(
    val selectedDaysOfWeek: List<DayOfWeek> = emptyList(),
)

private class DayOfWeekPickerPreviewParameterProvider : PreviewParameterProvider<DayOfWeekPickerPreviewData> {
    override val values = sequenceOf(
        // No Days of Week Selected
        DayOfWeekPickerPreviewData(),
        // Monday Selected
        DayOfWeekPickerPreviewData(selectedDaysOfWeek = listOf(DayOfWeek.MONDAY)),
        // All Days of Week Selected
        DayOfWeekPickerPreviewData(selectedDaysOfWeek = DayOfWeek.entries),
    )
}

@Preview
@Composable
private fun DayOfWeekPickerPreview(
    @PreviewParameter(DayOfWeekPickerPreviewParameterProvider::class) previewData: DayOfWeekPickerPreviewData,
) {
    DayOfWeekPicker(
        selectedDaysOfWeek = previewData.selectedDaysOfWeek,
        onDayOfWeekClick = {},
    )
}
//endregion
