package com.example.habittracker.ui.screens.habits.add.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.habittracker.ui.composables.dialogs.DialogCallbacks
import com.example.habittracker.ui.composables.dialogs.GenericDialog
import com.example.habittracker.ui.composables.input.HabitTrackerTextField
import com.example.habittracker.ui.composables.topbar.TopBar
import com.example.habittracker.ui.screens.habits.add.task.TaskEntryDialogType.Confirmation.Delete.getDialogResources
import com.example.habittracker.ui.screens.habits.add.task.composables.ConfirmTaskButton
import com.example.habittracker.ui.screens.habits.add.task.composables.DeleteTaskButton
import com.example.habittracker.ui.screens.habits.add.task.section.DaysOfWeekSection
import com.example.habittracker.ui.screens.habits.add.task.section.TimeSection
import com.example.habittracker.ui.screens.habits.model.TaskEntryUIData
import com.example.habittracker.ui.theme.HabitTrackerColors
import com.example.habittracker.ui.utils.previews.Mocks
import com.example.habittracker.ui.utils.testTags.TestTagState
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun TaskEntryRoute(
    viewModel: TaskEntryViewModel = hiltViewModel(),
    taskEntryFlow: TaskEntryFlow,
    navigateBack: () -> Unit,
) {
    val taskEntryData by viewModel.taskEntryData.collectAsStateWithLifecycle()
    val isTimePickerVisible by viewModel.isTimePickerVisible.collectAsStateWithLifecycle()
    val isConfirmEnabled by viewModel.isConfirmEnabled.collectAsStateWithLifecycle()
    val isEditFlow by viewModel.isEditFlow.collectAsStateWithLifecycle()
    val isDialogVisible by viewModel.isDialogVisible.collectAsStateWithLifecycle()
    val dialogCallbacks by viewModel.dialogCallbacks.collectAsStateWithLifecycle()
    val dialogType by viewModel.dialogType.collectAsStateWithLifecycle()

    val uiEvent by viewModel.uiEvent.collectAsStateWithLifecycle(null)

    LaunchedEffect(taskEntryFlow) {
        viewModel.loadTask(taskEntryFlow)
    }

    LaunchedEffect(uiEvent) {
        when (uiEvent) {
            TaskEntryViewModel.AddTaskEvent.NavigateBack -> navigateBack()
            null -> {}
        }
    }

    TaskEntryScreen(
        taskEntryData = taskEntryData,
        isEditFlow = isEditFlow,
        isTimePickerVisible = isTimePickerVisible,
        isDialogVisible = isDialogVisible,
        dialogType = dialogType,
        dialogCallbacks = dialogCallbacks,
        isConfirmEnabled = isConfirmEnabled,
        onCloseClick = navigateBack,
        updateTaskName = viewModel::updateName,
        onDayOfWeekClick = viewModel::onDayOfWeekClick,
        onTimeClick = viewModel::onTimeClick,
        onTimePickerDismiss = viewModel::onTimePickerDismiss,
        onTimePickerConfirm = viewModel::onTimePickerConfirm,
        onConfirmTaskEntryClick = viewModel::onConfirmTaskEntryClick,
        onDeleteTaskClick = viewModel::onDeleteTaskClick,
    )
}

@Composable
fun TaskEntryScreen(
    taskEntryData: TaskEntryUIData,
    isEditFlow: Boolean,
    isTimePickerVisible: Boolean,
    isDialogVisible: Boolean,
    dialogType: TaskEntryDialogType,
    dialogCallbacks: DialogCallbacks,
    isConfirmEnabled: Boolean,
    onCloseClick: () -> Unit,
    onDayOfWeekClick: (DayOfWeek) -> Unit,
    updateTaskName: (String) -> Unit,
    onTimeClick: () -> Unit,
    onTimePickerDismiss: () -> Unit,
    onTimePickerConfirm: (time: LocalTime?) -> Unit,
    onConfirmTaskEntryClick: () -> Unit,
    onDeleteTaskClick: () -> Unit,
) {
    val testTagState = TestTagState("TaskEntryScreen")

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(
                    id = if (isEditFlow) {
                        R.string.task_entry_screen_toolbar_title_edit
                    } else {
                        R.string.task_entry_screen_toolbar_title_add
                    }
                ),
                hasNavigationIcon = true,
                onNavigationIconClick = onCloseClick,
                navigationIconColor = HabitTrackerColors.green700,
            )
        },
    ) { paddingValues ->
        Content(
            taskEntryData = taskEntryData,
            updateTaskName = updateTaskName,
            testTagState = testTagState,
            onDayOfWeekClick = onDayOfWeekClick,
            onTimeClick = onTimeClick,
            isTimePickerVisible = isTimePickerVisible,
            onTimePickerConfirm = onTimePickerConfirm,
            onTimePickerDismiss = onTimePickerDismiss,
            isConfirmEnabled = isConfirmEnabled,
            isEditFlow = isEditFlow,
            onConfirmTaskEntryClick = onConfirmTaskEntryClick,
            onDeleteTaskClick = onDeleteTaskClick,
            modifier = Modifier.padding(paddingValues)
        )

        GenericDialog(
            isVisible = isDialogVisible,
            resources = getDialogResources(dialogType),
            callbacks = dialogCallbacks
        )
    }
}

@Composable
private fun Content(
    taskEntryData: TaskEntryUIData,
    updateTaskName: (String) -> Unit,
    testTagState: TestTagState,
    onDayOfWeekClick: (DayOfWeek) -> Unit,
    onTimeClick: () -> Unit,
    isTimePickerVisible: Boolean,
    onTimePickerConfirm: (time: LocalTime?) -> Unit,
    onTimePickerDismiss: () -> Unit,
    isConfirmEnabled: Boolean,
    isEditFlow: Boolean,
    onConfirmTaskEntryClick: () -> Unit,
    onDeleteTaskClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        HabitTrackerTextField(
            value = taskEntryData.name,
            onValueChange = updateTaskName,
            label = stringResource(id = R.string.task_entry_screen_task_name_text_field_label),
            labelIconResId = R.drawable.ic_habits_16dp,
            placeholder = stringResource(id = R.string.task_entry_screen_task_name_text_field_placeholder),
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

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            ConfirmTaskButton(
                isConfirmEnabled = isConfirmEnabled,
                isEditFlow = isEditFlow,
                onConfirmClick = onConfirmTaskEntryClick,
                testTagState = testTagState.section("ConfirmAddTaskSection")
            )

            if (isEditFlow) {
                DeleteTaskButton(
                    onClick = onDeleteTaskClick,
                    testTagState = testTagState
                )
            }
        }
    }
}

//region --- Preview ---
private data class AddTaskScreenPreviewData(
    val taskEntryData: TaskEntryUIData = Mocks.taskEntryUIData_empty,
    val isTimePickerVisible: Boolean = false,
    val isEditFlow: Boolean = false,
)

private class AddTaskScreenPreviewParameterProvider : PreviewParameterProvider<AddTaskScreenPreviewData> {
    override val values = sequenceOf(
        // Initial state
        AddTaskScreenPreviewData(),

        // Data Added
        AddTaskScreenPreviewData(taskEntryData = Mocks.taskEntryUIData_1),

        // Edit Flow
        AddTaskScreenPreviewData(isEditFlow = true),
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
        isEditFlow = previewData.isEditFlow,
        isTimePickerVisible = previewData.isTimePickerVisible,
        isConfirmEnabled = isConfirmEnabled,
        isDialogVisible = false,
        dialogType = TaskEntryDialogType.Confirmation.Delete,
        dialogCallbacks = DialogCallbacks.BasicDialog(),
        onCloseClick = {},
        onDayOfWeekClick = {},
        updateTaskName = {},
        onTimeClick = {},
        onTimePickerDismiss = {},
        onTimePickerConfirm = {},
        onConfirmTaskEntryClick = {},
        onDeleteTaskClick = {},
    )
}
//endregion
