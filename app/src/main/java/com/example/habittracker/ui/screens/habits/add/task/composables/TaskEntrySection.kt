package com.example.habittracker.ui.screens.habits.add.task.composables

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
fun TaskEntrySection(
    title: String,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxWidth(),
    ) {
        Header(
            title = title,
            testTagState = testTagState,
        )

        content()
    }
}

@Composable
private fun Header(
    title: String,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = title,
            style = HabitTrackerTypography.subtitle1,
            color = HabitTrackerColors.green700,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("${testTagState.origin}${testTagState.section}Title"),
        )
    }
}
