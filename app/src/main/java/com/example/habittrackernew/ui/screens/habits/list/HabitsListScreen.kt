package com.example.habittrackernew.ui.screens.habits.list

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.example.habittrackernew.ui.composables.cards.HabitCard
import com.example.habittrackernew.ui.composables.topbar.TopBar
import com.example.habittrackernew.ui.screens.habits.model.HabitUIData
import com.example.habittrackernew.ui.utils.previews.Mocks
import com.example.habittrackernew.ui.utils.testTags.TestTagState

@Composable
fun HabitsListRoute(
    viewModel: HabitsListViewModel = hiltViewModel(),
    onAddHabitClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HabitsListScreen(
        uiState = uiState,
        onAddHabitClick = onAddHabitClick,
        onHabitCardClick = {
            // TODO: Add  navigation to
        },
    )
}

@Composable
fun HabitsListScreen(
    uiState: HabitsListUIState,
    onAddHabitClick: () -> Unit,
    onHabitCardClick: (id: String) -> Unit,
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
        AnimatedContent(
            targetState = uiState,
            label = "HabitsListScreen",
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) { uiState ->
            when (uiState) {
                is HabitsListUIState.Success -> {
                    HabitsList(
                        habits = uiState.habits,
                        onHabitCardClick = onHabitCardClick,
                        testTagState = testTagState
                    )
                }

                is HabitsListUIState.Loading -> {

                }
            }
        }
    }
}

@Composable
fun HabitsList(
    habits: List<HabitUIData>,
    onHabitCardClick: (id: String) -> Unit,
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
                onClick = { onHabitCardClick(habit.id) },
                testTagState = testTagState.index(index)
            )
        }
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
        onHabitCardClick = {}
    )
}
//endregion
