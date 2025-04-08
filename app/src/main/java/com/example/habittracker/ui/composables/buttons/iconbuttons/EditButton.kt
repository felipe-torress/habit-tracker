package com.example.habittracker.ui.composables.buttons.iconbuttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.habittracker.R
import com.example.habittracker.ui.screens.habits.model.ColorUI
import com.example.habittracker.ui.theme.HabitTrackerColors
import com.example.habittracker.ui.utils.compose.rememberInteractionsSource
import com.example.habittracker.ui.utils.testTags.TestTagState

@Composable
fun EditButton(
    onClick: () -> Unit,
    color: ColorUI,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    val (iconColor, rippleColor) = remember {
        when (color) {
            ColorUI.BLUE -> HabitTrackerColors.blue700 to HabitTrackerColors.blue500
            ColorUI.GREEN -> HabitTrackerColors.green700 to HabitTrackerColors.green500
            ColorUI.PURPLE -> HabitTrackerColors.purple700 to HabitTrackerColors.purple500
        }
    }

    Icon(
        painter = painterResource(id = R.drawable.ic_pencil_24dp),
        contentDescription = null,
        tint = iconColor,
        modifier = modifier
            .size(24.dp)
            .clickable(
                interactionSource = rememberInteractionsSource(),
                indication = ripple(
                    color = rippleColor,
                    bounded = false,
                    radius = 24.dp,
                ),
            ) { onClick() }
            .testTag("${testTagState.origin}EditButton"),
    )
}
