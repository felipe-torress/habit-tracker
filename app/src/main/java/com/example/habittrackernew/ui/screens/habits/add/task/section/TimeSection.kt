package com.example.habittrackernew.ui.screens.habits.add.task.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.composables.dialogs.TimePickerDialog
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.theme.HabitTrackerTypography
import com.example.habittrackernew.ui.utils.compose.rememberInteractionsSource
import com.example.habittrackernew.ui.utils.datetime.toLocalizedTime
import java.time.LocalTime

@Composable
fun TimeSection(
    time: LocalTime?,
    onClick: () -> Unit,
    isTimePickerVisible: Boolean,
    onTimePickerConfirm: (localTime: LocalTime) -> Unit,
    onTimePickerDismiss: () -> Unit,
) {
    TimeContainer(time = time, onClick = onClick)

    if (isTimePickerVisible) {
        TimePickerDialog(
            onCancel = onTimePickerDismiss,
            onConfirm = onTimePickerConfirm,
        )
    }
}

@Composable
private fun TimeContainer(
    time: LocalTime?,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                interactionSource = rememberInteractionsSource(),
                indication = ripple(color = HabitTrackerColors.green500),
            ) {
                onClick()
            }
            .fillMaxWidth()
            .background(color = HabitTrackerColors.softGreen)
            .padding(16.dp),
    ) {
        Label()
        Time(time)
    }
}

@Composable
private fun Label(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_clock_16dp),
            contentDescription = null,
            tint = HabitTrackerColors.green700,
            modifier = Modifier.size(16.dp),
        )

        Text(
            text = stringResource(R.string.add_task_screen_time_picker_label),
            style = HabitTrackerTypography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = HabitTrackerColors.green700,
        )
    }
}

@Composable
private fun RowScope.Time(
    time: LocalTime?,
    modifier: Modifier = Modifier,
) {
    val (text, textColor) = if (time != null) {
        time.toLocalizedTime() to HabitTrackerColors.textColor
    } else {
        stringResource(R.string.add_task_screen_time_picker_placeholder) to HabitTrackerColors.green500
    }

    Text(
        text = text,
        style = HabitTrackerTypography.bodyLarge,
        color = textColor,
        textAlign = TextAlign.Left,
        modifier = modifier.weight(1f),
    )
}
