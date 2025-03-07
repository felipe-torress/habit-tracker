package com.example.habittrackernew.ui.screens.habits.details.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittrackernew.ui.composables.buttons.iconbuttons.EditButton
import com.example.habittrackernew.ui.screens.habits.model.ColorUI
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.theme.HabitTrackerTypography
import com.example.habittrackernew.ui.utils.testTags.TestTagState

@Composable
fun Header(
    name: String,
    color: ColorUI,
    onEditNameClick: () -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = when (color) {
        ColorUI.BLUE -> HabitTrackerColors.softBlue
        ColorUI.GREEN -> HabitTrackerColors.softGreen
        ColorUI.PURPLE -> HabitTrackerColors.softPurple
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            .background(backgroundColor)
            .padding(16.dp),
    ) {
        Text(
            text = name,
            style = HabitTrackerTypography.subtitle1.copy(fontSize = 24.sp),
            color = HabitTrackerColors.textColor,
            modifier = Modifier
                .weight(1f)
                .testTag("${testTagState.origin}HabitName"),
        )

        EditButton(
            onClick = onEditNameClick,
            color = color,
            testTagState = testTagState,
        )
    }
}
