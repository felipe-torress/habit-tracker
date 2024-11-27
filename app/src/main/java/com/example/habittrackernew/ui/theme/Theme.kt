package com.example.habittrackernew.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val colorScheme = lightColorScheme(
    primary = HabitTrackerColors.green500,
    onPrimary = HabitTrackerColors.backgroundColor,
    secondary = HabitTrackerColors.blue500,
    onSecondary = HabitTrackerColors.backgroundColor,
    background = HabitTrackerColors.backgroundColor,
    onBackground = HabitTrackerColors.textColor,
)

@Composable
fun HabitTrackerTheme(content: @Composable () -> Unit) =
    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
