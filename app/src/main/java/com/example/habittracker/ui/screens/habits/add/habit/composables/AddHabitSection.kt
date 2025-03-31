package com.example.habittracker.ui.screens.habits.add.habit.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.habittracker.ui.theme.HabitTrackerColors
import com.example.habittracker.ui.theme.HabitTrackerTypography
import com.example.habittracker.ui.utils.testTags.TestTagState

@Composable
fun AddHabitSection(
    title: String,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
    description: String? = null,
    content: @Composable () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxWidth(),
    ) {
        Header(
            title = title,
            description = description,
            testTagState = testTagState,
        )

        content()
    }
}

@Composable
private fun Header(
    title: String,
    description: String?,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth(),
    ) {
        Title(
            title = title,
            testTagState = testTagState,
        )

        description?.let {
            Description(
                description = it,
                testTagState = testTagState,
            )
        }
    }
}

@Composable
private fun Title(
    title: String,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = HabitTrackerTypography.subtitle1,
        color = HabitTrackerColors.green700,
        modifier = modifier
            .fillMaxWidth()
            .testTag("${testTagState.origin}${testTagState.section}Title"),
    )
}

@Composable
private fun Description(
    description: String,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Text(
        text = description,
        style = HabitTrackerTypography.bodySmall,
        color = HabitTrackerColors.textColor,
        modifier = modifier
            .fillMaxWidth()
            .testTag("${testTagState.origin}${testTagState.section}Description"),
    )
}
