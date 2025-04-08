package com.example.habittracker.ui.screens.habits.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.habittracker.MainActivityViewModel
import com.example.habittracker.R
import com.example.habittracker.ui.composables.buttons.BasicButton
import com.example.habittracker.ui.composables.dialogs.DialogCallbacks
import com.example.habittracker.ui.composables.dialogs.GenericDialog
import com.example.habittracker.ui.composables.dialogs.InputDialog
import com.example.habittracker.ui.composables.loading.Loader
import com.example.habittracker.ui.composables.topbar.GradientTopBar
import com.example.habittracker.ui.screens.habits.add.task.TaskEntryFlow
import com.example.habittracker.ui.screens.habits.details.HabitDetailsDialogType.Companion.getDialogResources
import com.example.habittracker.ui.screens.habits.details.composables.Header
import com.example.habittracker.ui.screens.habits.details.sections.tasksSection
import com.example.habittracker.ui.screens.habits.model.ColorUI
import com.example.habittracker.ui.screens.habits.model.HabitUIData
import com.example.habittracker.ui.theme.HabitTrackerColors
import com.example.habittracker.ui.utils.di.activityHiltViewModel
import com.example.habittracker.ui.utils.previews.Mocks
import com.example.habittracker.ui.utils.testTags.TestTagState

@Composable
fun HabitDetailsRoute(
    viewModel: HabitDetailsViewModel = hiltViewModel(),
    mainActivityViewModel: MainActivityViewModel = activityHiltViewModel(),
    habitId: String,
    navigateBack: () -> Unit,
    navigateToTaskEntry: (taskEntryFlow: TaskEntryFlow) -> Unit,
) {
    val habitUIState by viewModel.habitUIState.collectAsStateWithLifecycle()
    val isDialogVisible by viewModel.isDialogVisible.collectAsStateWithLifecycle()
    val dialogType by viewModel.dialogType.collectAsStateWithLifecycle()
    val dialogCallbacks by viewModel.dialogCallbacks.collectAsStateWithLifecycle()
    val temporaryHabitName by viewModel.temporaryHabitName.collectAsStateWithLifecycle()

    // Refresh the habit data whenever the screen initializes
    LaunchedEffect(habitId) {
        viewModel.refreshHabit(habitId)
    }

    val uiEvent by viewModel.uiEvent.collectAsStateWithLifecycle(null)
    LaunchedEffect(uiEvent) {
        when (uiEvent) {
            is HabitDetailsViewModel.HabitDetailsEvent.NavigateBack -> navigateBack()
            is HabitDetailsViewModel.HabitDetailsEvent.NavigateToTaskEntry -> {
                val taskEntryFlow = (uiEvent as HabitDetailsViewModel.HabitDetailsEvent.NavigateToTaskEntry).taskEntryFlow
                navigateToTaskEntry(taskEntryFlow)
            }

            else -> {}
        }
    }

    DisposableEffect(habitUIState) {
        // Set custom Status Bar color
        if (habitUIState is HabitDetailsUIState.Success) {
            val habit = (habitUIState as HabitDetailsUIState.Success).habit
            val color = when (habit.color) {
                ColorUI.BLUE -> HabitTrackerColors.blue200
                ColorUI.GREEN -> HabitTrackerColors.green200
                ColorUI.PURPLE -> HabitTrackerColors.purple100
            }
            mainActivityViewModel.updateStatusBarColor(color)
        }

        // Reset to default Status Bar color when leaving the screen
        onDispose {
            mainActivityViewModel.resetStatusBarColor()
        }
    }

    HabitDetailsScreen(
        habitUIState = habitUIState,
        isDialogVisible = isDialogVisible,
        dialogType = dialogType,
        dialogCallbacks = dialogCallbacks,
        temporaryHabitName = temporaryHabitName,
        onCloseClick = viewModel::onCloseClick,
        onDeleteClick = viewModel::onDeleteClick,
        onEditTaskClick = viewModel::onEditTaskClick,
        onEditNameClick = viewModel::onEditNameClick,
        onAddTask = viewModel::onAddTask,
    )
}

@Composable
fun HabitDetailsScreen(
    habitUIState: HabitDetailsUIState,
    isDialogVisible: Boolean,
    dialogType: HabitDetailsDialogType,
    dialogCallbacks: DialogCallbacks,
    temporaryHabitName: String,
    onCloseClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onAddTask: () -> Unit,
    onEditTaskClick: (taskId: String) -> Unit,
    onEditNameClick: () -> Unit,
) {
    val testTagState = TestTagState("HabitDetailsScreen")

    AnimatedContent(
        targetState = habitUIState,
        label = "HabitDetailsScreen",
        modifier = Modifier.fillMaxSize()
    ) { state ->
        when (state) {
            HabitDetailsUIState.Failure -> Box(modifier = Modifier.fillMaxSize())
            HabitDetailsUIState.Loading -> Loader()
            is HabitDetailsUIState.Success -> {
                SuccessContent(
                    habit = state.habit,
                    onCloseClick = onCloseClick,
                    testTagState = testTagState,
                    onDeleteClick = onDeleteClick,
                    onEditTaskClick = onEditTaskClick,
                    onEditNameClick = onEditNameClick,
                    onAddTask = onAddTask,
                )
            }
        }
    }

    Dialogs(
        isDialogVisible = isDialogVisible,
        dialogType = dialogType,
        dialogCallbacks = dialogCallbacks,
        temporaryHabitName = temporaryHabitName,
    )
}

@Composable
private fun Dialogs(
    isDialogVisible: Boolean,
    dialogType: HabitDetailsDialogType,
    dialogCallbacks: DialogCallbacks,
    temporaryHabitName: String,
) {
    val resources = getDialogResources(dialogType)
    when (dialogType) {
        is HabitDetailsDialogType.BasicDialog -> {
            GenericDialog(
                isVisible = isDialogVisible,
                resources = resources,
                callbacks = dialogCallbacks,
            )
        }

        is HabitDetailsDialogType.InputDialog.RenameHabit -> {
            InputDialog(
                isVisible = isDialogVisible,
                textFieldValue = temporaryHabitName,
                resources = resources,
                callbacks = dialogCallbacks,
            )
        }
    }
}

@Composable
fun SuccessContent(
    habit: HabitUIData,
    onCloseClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditTaskClick: (taskId: String) -> Unit,
    onAddTask: () -> Unit,
    onEditNameClick: () -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            GradientTopBar(
                title = stringResource(R.string.habit_details_screen_toolbar_title),
                hasNavigationIcon = true,
                onNavigationIconClick = onCloseClick,
                color = habit.color,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Header(
                name = habit.name,
                color = habit.color,
                onEditNameClick = onEditNameClick,
                testTagState = testTagState
            )

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                tasksSection(
                    tasks = habit.tasks,
                    color = habit.color,
                    testTagState = testTagState,
                    onEditTaskClick = onEditTaskClick,
                    onAddTask = onAddTask,
                )

                deleteButton(
                    onClick = onDeleteClick,
                    testTagState = testTagState
                )
            }
        }
    }
}

private fun LazyListScope.deleteButton(
    onClick: () -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier
) {
    item {
        BasicButton(
            text = stringResource(R.string.habit_details_screen_delete_button_title),
            iconResId = R.drawable.ic_trash_24dp,
            colors = ButtonDefaults.buttonColors(
                containerColor = HabitTrackerColors.red50,
                contentColor = HabitTrackerColors.red500,
            ),
            onClick = onClick,
            testTagState = testTagState.action("Delete"),
            modifier = modifier.padding(top = 24.dp)
        )
    }
}

//region --- Preview ---
private data class HabitDetailsScreenPreviewData(
    val habitUIState: HabitDetailsUIState = HabitDetailsUIState.Loading,
)

private class HabitDetailsScreenPreviewParameterProvider : PreviewParameterProvider<HabitDetailsScreenPreviewData> {
    override val values = sequenceOf(
        // Initial State - Loading
        HabitDetailsScreenPreviewData(),

        // Success State - Blue
        HabitDetailsScreenPreviewData(
            habitUIState = HabitDetailsUIState.Success(
                habit = Mocks.habitUIData_1
            )
        ),

        // Success State - Purple
        HabitDetailsScreenPreviewData(
            habitUIState = HabitDetailsUIState.Success(
                habit = Mocks.habitUIData_purple
            )
        ),

        // Success State - Green
        HabitDetailsScreenPreviewData(
            habitUIState = HabitDetailsUIState.Success(
                habit = Mocks.habitUIData_green
            )
        ),
    )
}

@Preview
@Composable
private fun HabitDetailsScreenPreview(@PreviewParameter(HabitDetailsScreenPreviewParameterProvider::class) previewData: HabitDetailsScreenPreviewData) {
    HabitDetailsScreen(
        habitUIState = previewData.habitUIState,
        isDialogVisible = false,
        dialogType = HabitDetailsDialogType.BasicDialog.DeleteHabit,
        dialogCallbacks = DialogCallbacks.BasicDialog(),
        temporaryHabitName = Mocks.habitUIData_1.name,
        onCloseClick = {},
        onDeleteClick = {},
        onEditTaskClick = {},
        onEditNameClick = {},
        onAddTask = {},
    )
}
//endregion
