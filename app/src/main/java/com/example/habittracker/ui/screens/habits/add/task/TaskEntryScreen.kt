package com.example.habittracker.ui.screens.habits.add.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.habittracker.R
import com.example.habittracker.ui.composables.input.HabitTrackerTextField
import com.example.habittracker.ui.composables.topbar.TopBar
import com.example.habittracker.ui.screens.habits.add.task.section.ConfirmTaskSection
import com.example.habittracker.ui.screens.habits.add.task.section.DaysOfWeekSection
import com.example.habittracker.ui.screens.habits.add.task.section.TimeSection
import com.example.habittracker.ui.screens.habits.model.TaskEntryUIData
import com.example.habittracker.ui.theme.HabitTrackerColors
import com.example.habittracker.ui.utils.previews.MockConstants
import com.example.habittracker.ui.utils.previews.Mocks
import com.example.habittracker.ui.utils.testTags.TestTagState
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun TaskEntryRoute(
    viewModel: TaskEntryViewModel = hiltViewModel(),
    taskId: String?,
    isSavedTask: Boolean,
    navigateBack: () -> Unit,
) {
    val taskEntryData by viewModel.taskEntryData.collectAsStateWithLifecycle()
    val isTimePickerVisible by viewModel.isTimePickerVisible.collectAsStateWithLifecycle()
    val isConfirmEnabled by viewModel.isConfirmEnabled.collectAsStateWithLifecycle()

    val uiEvent by viewModel.uiEvent.collectAsStateWithLifecycle(null)

    LaunchedEffect(taskId, isSavedTask) {
        taskId?.let {
            viewModel.loadTask(taskId = taskId, isSavedTask = isSavedTask)
        }
    }

    LaunchedEffect(uiEvent) {
        when (uiEvent) {
            TaskEntryViewModel.AddTaskEvent.NavigateBack -> navigateBack()
            null -> {}
        }
    }

    TaskEntryScreen(
        taskEntryData = taskEntryData,
        isTimePickerVisible = isTimePickerVisible,
        isConfirmEnabled = isConfirmEnabled,
        onCloseClick = navigateBack,
        updateTaskName = viewModel::updateName,
        onDayOfWeekClick = viewModel::onDayOfWeekClick,
        onTimeClick = viewModel::onTimeClick,
        onTimePickerDismiss = viewModel::onTimePickerDismiss,
        onTimePickerConfirm = viewModel::onTimePickerConfirm,
        onConfirmAddTaskClick = viewModel::addTask,
    )
}

@Composable
fun TaskEntryScreen(
    taskEntryData: TaskEntryUIData,
    isTimePickerVisible: Boolean,
    isConfirmEnabled: Boolean,
    onCloseClick: () -> Unit,
    onDayOfWeekClick: (DayOfWeek) -> Unit,
    updateTaskName: (String) -> Unit,
    onTimeClick: () -> Unit,
    onTimePickerDismiss: () -> Unit,
    onTimePickerConfirm: (time: LocalTime?) -> Unit,
    onConfirmAddTaskClick: () -> Unit,
) {
    val testTagState = TestTagState("TaskEntryScreen")

    Scaffold(
        topBar = {
            TopBar(
                // TODO: Make text dynamic
                title = stringResource(id = R.string.add_task_screen_toolbar_title),
                hasNavigationIcon = true,
                onNavigationIconClick = onCloseClick,
                navigationIconColor = HabitTrackerColors.green700,
            )
        },
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        ) {
            HabitTrackerTextField(
                value = taskEntryData.name,
                onValueChange = updateTaskName,
                label = stringResource(id = R.string.add_task_screen_task_name_text_field_label),
                labelIconResId = R.drawable.ic_habits_16dp,
                placeholder = stringResource(id = R.string.add_task_screen_task_name_text_field_placeholder),
                testTagState = testTagState.type("TaskName"),
            )

            DaysOfWeekSection(
                selectedDaysOfWeek = taskEntryData.daysOfWeek,
                onDayOfWeekClick = onDayOfWeekClick,
                testTagState = testTagState.section("DaysOfWeekSection")
            )

            TimeSection(
                time = taskEntryData.time,
                onClick = onTimeClick,
                isTimePickerVisible = isTimePickerVisible,
                onTimePickerConfirm = onTimePickerConfirm,
                onTimePickerDismiss = onTimePickerDismiss,
            )

            ConfirmTaskSection(
                isConfirmEnabled = isConfirmEnabled,
                onConfirmClick = onConfirmAddTaskClick,
                testTagState = testTagState.section("ConfirmAddTaskSection")
            )
        }
    }
}

//region --- Preview ---
private data class AddTaskScreenPreviewData(
    val taskEntryData: TaskEntryUIData = Mocks.taskEntryUIData_empty,
    val isTimePickerVisible: Boolean = false,
)

private class AddTaskScreenPreviewParameterProvider : PreviewParameterProvider<AddTaskScreenPreviewData> {
    override val values = sequenceOf(
        // Initial state
        AddTaskScreenPreviewData(),

        // Data Added
        AddTaskScreenPreviewData(taskEntryData = Mocks.taskEntryUIData_1),
    )
}

@Preview
@Composable
private fun AddTaskScreenPreview(@PreviewParameter(AddTaskScreenPreviewParameterProvider::class) previewData: AddTaskScreenPreviewData) {
    val name = previewData.taskEntryData.name
    val daysOfWeek = previewData.taskEntryData.daysOfWeek
    val time = previewData.taskEntryData.time
    val isConfirmEnabled = name.isNotBlank() && daysOfWeek.isNotEmpty() && time != null

    TaskEntryScreen(
        taskEntryData = previewData.taskEntryData,
        isTimePickerVisible = previewData.isTimePickerVisible,
        isConfirmEnabled = isConfirmEnabled,
        onCloseClick = {},
        onDayOfWeekClick = {},
        updateTaskName = {},
        onTimeClick = {},
        onTimePickerDismiss = {},
        onTimePickerConfirm = {},
        onConfirmAddTaskClick = {}
    )
}
//endregion
