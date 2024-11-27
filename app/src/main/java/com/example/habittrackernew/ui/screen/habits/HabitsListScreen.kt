package com.example.habittrackernew.ui.screen.habits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.composables.topbar.TopBar

@Composable
fun HabitsListRoute(
    viewModel: HabitsListViewModel = hiltViewModel(),
    onAddHabitClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HabitsListScreen(
        uiState = uiState,
        onAddHabitClick = onAddHabitClick,
    )
}

@Composable
fun HabitsListScreen(
    uiState: HabitsListUIState,
    onAddHabitClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.habits_tab_title),
                hasNavigationIcon = false,
                hasActionButton = true,
                onActionButtonClick = onAddHabitClick,
            )
        },
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
        ) {
            Text(stringResource(id = R.string.habits_tab_title))
        }
    }
}
