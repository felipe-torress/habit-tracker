package com.example.habittracker.ui.screens.habits.list

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
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
import com.example.habittracker.R
import com.example.habittracker.ui.composables.cards.HabitCard
import com.example.habittracker.ui.composables.loading.HabitTrackerPullToRefreshBox
import com.example.habittracker.ui.composables.topbar.TopBar
import com.example.habittracker.ui.screens.habits.add.task.TaskEntryFlow
import com.example.habittracker.ui.screens.habits.model.HabitUIData
import com.example.habittracker.ui.theme.HabitTrackerColors
import com.example.habittracker.ui.theme.HabitTrackerTypography
import com.example.habittracker.ui.utils.previews.Mocks
import com.example.habittracker.ui.utils.testTags.TestTagState

@Composable
fun HabitsListRoute(
    viewModel: HabitsListViewModel = hiltViewModel(),
    navigateToAddHabit: () -> Unit,
    navigateToHabitDetails: (habitId: String) -> Unit,
    navigateToTaskEntry: (taskEntryFlow: TaskEntryFlow) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val uiEvent by viewModel.uiEvent.collectAsStateWithLifecycle(null)
    LaunchedEffect(uiEvent) {
        when (uiEvent) {
            is HabitsListViewModel.HabitsListEvent.NavigateToAddHabit -> navigateToAddHabit()
            is HabitsListViewModel.HabitsListEvent.NavigateToHabitDetails -> {
                val habitId = (uiEvent as HabitsListViewModel.HabitsListEvent.NavigateToHabitDetails).habitId
                navigateToHabitDetails(habitId)
            }

            is HabitsListViewModel.HabitsListEvent.NavigateToTaskEntry -> {
                val taskEntryFlow = (uiEvent as HabitsListViewModel.HabitsListEvent.NavigateToTaskEntry).taskEntryFlow
                navigateToTaskEntry(taskEntryFlow)
            }

            else -> {}
        }
    }

    HabitsListScreen(
        uiState = uiState,
        onRefresh = viewModel::refresh,
        onAddHabitClick = viewModel::onAddHabitClick,
        onHabitClick = viewModel::onHabitClick,
        onAddTaskClick = viewModel::onAddTaskClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitsListScreen(
    uiState: HabitsListUIState,
    onRefresh: () -> Unit,
    onAddHabitClick: () -> Unit,
    onHabitClick: (habitId: String) -> Unit,
    onAddTaskClick: (habitId: String) -> Unit,
) {
    val testTagState = TestTagState("HabitsListScreen")

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.habits_tab_title),
                hasActionButton = true,
                onActionButtonClick = onAddHabitClick,
            )
        },
    ) { paddingValues ->
        val pullToRefreshState = rememberPullToRefreshState()

        HabitTrackerPullToRefreshBox(
            isRefreshing = uiState is HabitsListUIState.Loading,
            onRefresh = onRefresh,
            state = pullToRefreshState,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Content(
                uiState = uiState,
                onHabitCardClick = onHabitClick,
                onAddTaskClick = onAddTaskClick,
                testTagState = testTagState
            )
        }
    }
}

@Composable
private fun Content(
    uiState: HabitsListUIState,
    onHabitCardClick: (habitId: String) -> Unit,
    onAddTaskClick: (habitId: String) -> Unit,
    testTagState: TestTagState
) {
    AnimatedContent(
        targetState = uiState,
        label = "HabitsListScreen",
        modifier = Modifier.fillMaxSize()
    ) { state ->
        when (state) {
            is HabitsListUIState.Success -> {
                HabitsList(
                    habits = state.habits,
                    onHabitCardClick = onHabitCardClick,
                    onAddTaskClick = onAddTaskClick,
                    testTagState = testTagState
                )
            }

            is HabitsListUIState.NoHabits -> NoHabitsPlaceholder()

            else -> Box(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun HabitsList(
    habits: List<HabitUIData>,
    onHabitCardClick: (habitId: String) -> Unit,
    onAddTaskClick: (habitId: String) -> Unit,
    testTagState: TestTagState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        itemsIndexed(
            items = habits,
            key = { index, item -> Pair(item.id, index) }
        ) { index, habit ->
            HabitCard(
                title = habit.name,
                daysOfWeek = habit.daysOfWeek,
                tasks = habit.tasks,
                color = habit.color,
                onHeaderClick = { onHabitCardClick(habit.id) },
                onAddTaskClick = { onAddTaskClick(habit.id) },
                testTagState = testTagState.index(index)
            )
        }
    }
}

@Composable
private fun NoHabitsPlaceholder() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.habit_list_screen_no_habits_placeholder_title),
            style = HabitTrackerTypography.subtitle1,
            color = HabitTrackerColors.green700
        )
    }
}

//region --- Preview ---
private data class HabitsListScreenPreviewData(
    val uiState: HabitsListUIState = HabitsListUIState.Success(habits = listOf(Mocks.habitUIData_1)),
)

private class HabitsListScreenPreviewParameterProvider : PreviewParameterProvider<HabitsListScreenPreviewData> {
    override val values = sequenceOf(
        HabitsListScreenPreviewData()
    )
}

@Preview
@Composable
private fun HabitsListScreenPreview(@PreviewParameter(HabitsListScreenPreviewParameterProvider::class) previewData: HabitsListScreenPreviewData) {
    HabitsListScreen(
        uiState = previewData.uiState,
        onAddHabitClick = {},
        onHabitClick = {},
        onAddTaskClick = {},
        onRefresh = {}
    )
}
//endregion
