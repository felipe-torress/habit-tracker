package com.example.habittrackernew.ui.screens.habits.add

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
import com.example.habittrackernew.ui.screens.habits.add.sections.ColorPickerSection
import com.example.habittrackernew.ui.screens.habits.add.sections.ReadyToStartSection
import com.example.habittrackernew.ui.screens.habits.add.sections.TasksSection
import com.example.habittrackernew.ui.screens.habits.model.ColorUI
import com.example.habittrackernew.ui.screens.habits.model.HabitTaskUIData
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.utils.previews.MockConstants
import com.example.habittrackernew.ui.utils.previews.Mocks
import com.example.habittrackernew.ui.utils.testTags.TestTagState

@Composable
fun AddHabitRoute(
    viewModel: AddHabitViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    onAddTaskClick: () -> Unit,
) {
    val name by viewModel.name.collectAsStateWithLifecycle()
    val color by viewModel.color.collectAsStateWithLifecycle()
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()

    val uiEvents by viewModel.uiEvents.collectAsStateWithLifecycle(null)

    LaunchedEffect(uiEvents) {
        when (uiEvents) {
            AddHabitViewModel.AddHabitEvent.NavigateBack -> navigateBack()
            null -> {}
        }
    }

    AddHabitScreen(
        name = name,
        color = color,
        tasks = tasks,
        onCloseClick = navigateBack,
        onAddTaskClick = onAddTaskClick,
        updateHabitName = viewModel::updateHabitName,
        onColorClick = viewModel::onColorClick,
        onAddHabitClick = viewModel::onAddHabitClick,
    )
}

@Composable
fun AddHabitScreen(
    name: String,
    color: ColorUI?,
    tasks: List<HabitTaskUIData>,
    onCloseClick: () -> Unit,
    updateHabitName: (String) -> Unit,
    onAddTaskClick: () -> Unit,
    onColorClick: (ColorUI) -> Unit,
    onAddHabitClick: () -> Unit,
) {
    val testTagState = TestTagState("HabitsListScreen")

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.add_habit_screen_toolbar_title),
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
                onValueChange = updateHabitName,
                label = stringResource(id = R.string.add_habit_screen_habit_text_field_label),
                labelIconResId = R.drawable.ic_habits_16dp,
                placeholder = stringResource(id = R.string.add_habit_screen_habit_text_field_placeholder),
                modifier = Modifier.padding(top = 16.dp)
            )

            ColorPickerSection(
                selectedColor = color,
                onColorClick = onColorClick,
                testTagState = testTagState.section("ColorPickerSection")
            )

            TasksSection(
                tasks = tasks,
                testTagState = testTagState.section("TasksSection"),
                onAddTaskClick = onAddTaskClick
            )

            ReadyToStartSection(
                onAddHabitClick = onAddHabitClick,
                testTagState = testTagState.section("ReadyToStartSection")
            )
        }
    }
}

//region --- Preview ---
private data class AddHabitScreenPreviewData(
    val name: String = "",
    val color: ColorUI? = null,
    val tasks: List<HabitTaskUIData> = emptyList()
)

private class AddHabitScreenPreviewParameterProvider : PreviewParameterProvider<AddHabitScreenPreviewData> {
    override val values = sequenceOf(
        // Empty State
        AddHabitScreenPreviewData(),

        // Name Filled
        AddHabitScreenPreviewData(name = MockConstants.habit_title_1),

        // Name Filled + Selected Color
        AddHabitScreenPreviewData(
            name = MockConstants.habit_title_1,
            color = ColorUI.GREEN,
        ),

        // Name Filled + Selected Color + Tasks
        AddHabitScreenPreviewData(
            name = MockConstants.habit_title_1,
            color = ColorUI.GREEN,
            tasks = listOf(
                Mocks.habitTaskUIData_1,
                Mocks.habitTaskUIData_2,
                Mocks.habitTaskUIData_3,
            )
        )
    )
}

@Preview
@Composable
private fun AddHabitScreenPreview(@PreviewParameter(AddHabitScreenPreviewParameterProvider::class) previewData: AddHabitScreenPreviewData) {
    AddHabitScreen(
        name = previewData.name,
        color = previewData.color,
        tasks = previewData.tasks,
        onCloseClick = {},
        onAddTaskClick = {},
        updateHabitName = {},
        onColorClick = {},
        onAddHabitClick = {},
    )
}
//endregion
