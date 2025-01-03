package com.example.habittrackernew.ui.screens.habits.add.task

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
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.composables.input.HabitTrackerTextField
import com.example.habittrackernew.ui.composables.topbar.TopBar
import com.example.habittrackernew.ui.screens.habits.add.task.section.ConfirmAddTaskSection
import com.example.habittrackernew.ui.screens.habits.add.task.section.DaysOfWeekSection
import com.example.habittrackernew.ui.screens.habits.add.task.section.TimeSection
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.utils.previews.MockConstants
import com.example.habittrackernew.ui.utils.testTags.TestTagState
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun AddTaskRoute(
    viewModel: AddTaskViewModel = hiltViewModel(),
    taskId: String?,
    navigateBack: () -> Unit,
) {
    val name by viewModel.name.collectAsStateWithLifecycle()
    val daysOfWeek by viewModel.daysOfWeek.collectAsStateWithLifecycle()
    val time by viewModel.time.collectAsStateWithLifecycle()
    val isTimePickerVisible by viewModel.isTimePickerVisible.collectAsStateWithLifecycle()
    val isConfirmEnabled by viewModel.isConfirmEnabled.collectAsStateWithLifecycle()

    val uiEvent by viewModel.uiEvent.collectAsStateWithLifecycle(null)

    LaunchedEffect(taskId) {
        taskId?.let {
            viewModel.loadTask(taskId)
        }
    }

    LaunchedEffect(uiEvent) {
        when (uiEvent) {
            AddTaskViewModel.AddTaskEvent.NavigateBack -> navigateBack()
            null -> {}
        }
    }

    AddTaskScreen(
        name = name,
        daysOfWeek = daysOfWeek,
        time = time,
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
fun AddTaskScreen(
    name: String,
    daysOfWeek: List<DayOfWeek>,
    time: LocalTime?,
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
    val testTagState = TestTagState("HabitsListScreen")

    Scaffold(
        topBar = {
            TopBar(
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
                value = name,
                onValueChange = updateTaskName,
                label = stringResource(id = R.string.add_task_screen_task_name_text_field_label),
                labelIconResId = R.drawable.ic_habits_16dp,
                placeholder = stringResource(id = R.string.add_task_screen_task_name_text_field_placeholder),
                testTagState = testTagState.type("TaskName"),
            )

            DaysOfWeekSection(
                selectedDaysOfWeek = daysOfWeek,
                onDayOfWeekClick = onDayOfWeekClick,
                testTagState = testTagState.section("DaysOfWeekSection")
            )

            TimeSection(
                time = time,
                onClick = onTimeClick,
                isTimePickerVisible = isTimePickerVisible,
                onTimePickerConfirm = onTimePickerConfirm,
                onTimePickerDismiss = onTimePickerDismiss,
            )

            ConfirmAddTaskSection(
                isConfirmEnabled = isConfirmEnabled,
                onConfirmClick = onConfirmAddTaskClick,
                testTagState = testTagState.section("ConfirmAddTaskSection")
            )
        }
    }
}

//region --- Preview ---
private data class AddTaskScreenPreviewData(
    val name: String = "",
    val daysOfWeek: List<DayOfWeek> = emptyList(),
    val time: LocalTime? = null,
    val isTimePickerVisible: Boolean = false,
)

private class AddTaskScreenPreviewParameterProvider : PreviewParameterProvider<AddTaskScreenPreviewData> {
    override val values = sequenceOf(
        // Initial state
        AddTaskScreenPreviewData(),

        // Name Added
        AddTaskScreenPreviewData(name = MockConstants.habit_title_1),

        // Name + Days Of Week Added
        AddTaskScreenPreviewData(
            name = MockConstants.habit_title_1,
            daysOfWeek = listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY)
        ),

        // Name + Days Of Week + Time Added
        AddTaskScreenPreviewData(
            name = MockConstants.habit_title_1,
            daysOfWeek = MockConstants.daysOfWeek_1,
            time = MockConstants.localTime_11_00_AM
        )
    )
}

@Preview
@Composable
private fun AddTaskScreenPreview(@PreviewParameter(AddTaskScreenPreviewParameterProvider::class) previewData: AddTaskScreenPreviewData) {
    val name = previewData.name
    val daysOfWeek = previewData.daysOfWeek
    val time = previewData.time

    AddTaskScreen(
        name = name,
        daysOfWeek = daysOfWeek,
        time = time,
        isTimePickerVisible = previewData.isTimePickerVisible,
        isConfirmEnabled = name.isNotBlank() && daysOfWeek.isNotEmpty() && time != null,
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
