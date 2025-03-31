package com.example.habittracker.ui.composables.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.habittracker.ui.theme.HabitTrackerColors

@Composable
fun Loader(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator(
            color = HabitTrackerColors.green500,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitTrackerPullToRefreshBox(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    state: PullToRefreshState,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    PullToRefreshBox(
        state = state,
        content = content,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        indicator = {
            PullToRefreshIndicator(
                isRefreshing = isRefreshing,
                state = state,
            )
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BoxScope.PullToRefreshIndicator(
    isRefreshing: Boolean,
    state: PullToRefreshState,
    modifier: Modifier = Modifier,
) {
    Indicator(
        modifier = modifier.align(Alignment.TopCenter),
        isRefreshing = isRefreshing,
        state = state,
        containerColor = HabitTrackerColors.green100,
        color = HabitTrackerColors.green900,
    )
}
