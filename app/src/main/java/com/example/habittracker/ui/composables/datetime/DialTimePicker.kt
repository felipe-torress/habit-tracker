package com.example.habittracker.ui.composables.datetime

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.habittracker.ui.theme.HabitTrackerColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialTimePicker(
    state: TimePickerState,
    modifier: Modifier = Modifier,
) {
    TimePicker(
        modifier = modifier,
        state = state,
        layoutType = TimePickerLayoutType.Vertical,
        colors = TimePickerDefaults.colors(
            // container
            containerColor = HabitTrackerColors.softGreen,
            // clock dial
            clockDialColor = HabitTrackerColors.softGreen,
            selectorColor = HabitTrackerColors.green700,
            // clock dial text (content)
            clockDialSelectedContentColor = HabitTrackerColors.green50,
            clockDialUnselectedContentColor = HabitTrackerColors.textColor,
            // time selector
            timeSelectorSelectedContainerColor = HabitTrackerColors.green700,
            timeSelectorUnselectedContainerColor = HabitTrackerColors.green100,
            timeSelectorSelectedContentColor = HabitTrackerColors.green50,
            timeSelectorUnselectedContentColor = HabitTrackerColors.textColor,
            // period selector
            periodSelectorBorderColor = HabitTrackerColors.green500,
            periodSelectorSelectedContainerColor = HabitTrackerColors.green700,
            periodSelectorUnselectedContainerColor = HabitTrackerColors.green100,
            periodSelectorSelectedContentColor = HabitTrackerColors.green50,
            periodSelectorUnselectedContentColor = HabitTrackerColors.textColor,
        ),
    )
}

//region --- Preview ---
private data class DialTimePickerPreviewData(
    val is24Hour: Boolean = false,
)

private class DialTimePickerPreviewParameterProvider : PreviewParameterProvider<DialTimePickerPreviewData> {
    override val values = sequenceOf(
        // 12 hour
        DialTimePickerPreviewData(),
        // 24 hour
        DialTimePickerPreviewData(is24Hour = true),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun DialTimePickerPreview(
    @PreviewParameter(DialTimePickerPreviewParameterProvider::class) previewData: DialTimePickerPreviewData,
) {
    DialTimePicker(state = rememberTimePickerState(is24Hour = previewData.is24Hour))
}

//endregion
