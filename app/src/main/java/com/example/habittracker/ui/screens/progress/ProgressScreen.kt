package com.example.habittracker.ui.screens.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.habittracker.R
import com.example.habittracker.ui.composables.topbar.TopBar

@Composable
fun ProgressRoute() {
    ProgressScreen()
}

@Composable
fun ProgressScreen() {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.progress_screen_toolbar_title),
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
            Text(stringResource(id = R.string.progress_tab_title))
        }
    }
}
