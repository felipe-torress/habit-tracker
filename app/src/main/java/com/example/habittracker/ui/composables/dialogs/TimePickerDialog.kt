package com.example.habittracker.ui.composables.dialogs

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.habittracker.R
import com.example.habittracker.ui.composables.datetime.DialTimePicker
import com.example.habittracker.ui.composables.datetime.InputTimePicker
import com.example.habittracker.ui.theme.HabitTrackerColors
import com.example.habittracker.ui.theme.HabitTrackerTypography
import com.example.habittracker.ui.utils.datetime.getLocalTime
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onCancel: () -> Unit,
    onConfirm: (localTime: LocalTime) -> Unit,
) {
    val initialZonedDateTime = LocalTime.now()

    // 24 hour time picker
    val state = rememberTimePickerState(
        is24Hour = true,
        initialHour = initialZonedDateTime.hour,
        initialMinute = initialZonedDateTime.minute,
    )

    val localTime = remember { derivedStateOf { getLocalTime(state.hour, state.minute) } }
    var mode by remember { mutableStateOf(TimePickerMode.Dial) }

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
                .padding(horizontal = 48.dp)
                .fillMaxWidth()
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
    AnimatedContent(
        contentAlignment = Alignment.Center,
        targetState = mode,
        label = "TimePíckerDialogContent",
        transitionSpec = {
            slideInVertically(
                animationSpec = tween(
                    durationMillis = 300,
                    delayMillis = 300,
                ),
                initialOffsetY = { height -> -height },
            ) +
                fadeIn(
                    animationSpec = tween(
                        durationMillis = 300,
                        delayMillis = 300,
                    ),
                ) togetherWith slideOutVertically(
                    animationSpec = tween(
                        durationMillis = 300,
                    ),
                    targetOffsetY = { height -> height },
                ) + fadeOut(
                    animationSpec = tween(
                        durationMillis = 300,
                    ),
                )
        },
    ) {
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
        style = HabitTrackerTypography.bodyLarge,
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
