package com.example.habittrackernew.ui.composables.dialogs

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.composables.datetime.DialTimePicker
import com.example.habittrackernew.ui.composables.datetime.InputTimePicker
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.theme.HabitTrackerTypography
import com.example.habittrackernew.ui.utils.datetime.getLocalTime
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onCancel: () -> Unit,
    onConfirm: (localTime: LocalTime) -> Unit,
) {
    // 24 hour time picker
    val state = rememberTimePickerState(is24Hour = true)

    val localTime = remember { derivedStateOf { getLocalTime(state.hour, state.minute) } }
    var mode = remember { TimePickerMode.Dial }

    fun switchMode() {
        mode = when (mode) {
            TimePickerMode.Dial -> TimePickerMode.Input
            TimePickerMode.Input -> TimePickerMode.Dial
        }
    }

    val shape = MaterialTheme.shapes.extraLarge

    Dialog(
        onDismissRequest = onCancel,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            shape = shape,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(color = HabitTrackerColors.backgroundColor, shape = shape),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Header()
                Content(state, mode)
                Footer(
                    mode = mode,
                    onSwitchMode = { switchMode() },
                    onCancel = onCancel,
                    onConfirm = onConfirm,
                    localTime = localTime,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    state: TimePickerState,
    mode: TimePickerMode,
) {
    AnimatedContent(targetState = mode, label = "TimePíckerDialogContent") {
        when (it) {
            TimePickerMode.Dial -> DialTimePicker(state)
            TimePickerMode.Input -> InputTimePicker(state)
        }
    }
}

@Composable
private fun Header() {
    Text(
        text = stringResource(id = R.string.time_picker_dialog_title),
        style = HabitTrackerTypography.caption,
        fontWeight = FontWeight.Bold,
        color = HabitTrackerColors.textColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
    )
}

@Composable
private fun Footer(
    mode: TimePickerMode,
    onSwitchMode: () -> Unit,
    onCancel: () -> Unit,
    onConfirm: (localTime: LocalTime) -> Unit,
    localTime: State<LocalTime>,
) {
    Row(
        modifier = Modifier
            .heightIn(40.dp)
            .fillMaxWidth(),
    ) {
        ToggleModeIcon(
            mode = mode,
            onClick = onSwitchMode,
        )
        Spacer(modifier = Modifier.weight(1f))
        Buttons(
            onCancel = onCancel,
            onConfirm = onConfirm,
            localTime = localTime.value,
        )
    }
}

@Composable
private fun ToggleModeIcon(
    mode: TimePickerMode,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val iconResId = when (mode) {
        TimePickerMode.Dial -> R.drawable.ic_keyboard_24dp
        TimePickerMode.Input -> R.drawable.ic_clock_24dp
    }

    IconButton(
        modifier = modifier.size(48.dp),
        onClick = onClick,
    ) {
        Icon(
            painter = painterResource(iconResId),
            tint = HabitTrackerColors.darkGrey400,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
    }
}

@Composable
private fun Buttons(
    onCancel: () -> Unit,
    onConfirm: (localTime: LocalTime) -> Unit,
    localTime: LocalTime,
) {
    TextButton(onClick = onCancel) {
        Text(
            text = stringResource(R.string.dialog_text_cancel),
            color = HabitTrackerColors.green900,
            style = HabitTrackerTypography.bodyLarge,
        )
    }
    TextButton(onClick = { onConfirm(localTime) }) {
        Text(
            text = stringResource(R.string.dialog_text_ok),
            color = HabitTrackerColors.green900,
            style = HabitTrackerTypography.bodyLarge,
        )
    }
}

enum class TimePickerMode {
    Dial,
    Input,
}

//region --- Preview ---
@Preview
@Composable
private fun TimePickerDialogPreview() {
    TimePickerDialog(
        onCancel = {},
        onConfirm = {},
    )
}
//endregion
