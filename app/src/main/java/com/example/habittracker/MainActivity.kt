package com.example.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.habittracker.ui.HabitTrackerApp
import com.example.habittracker.ui.theme.HabitTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !mainActivityViewModel.shouldHideSplashScreen.value
            }
        }

        enableEdgeToEdge()
        setContent {
            HabitTrackerTheme {
                val statusBarColor by mainActivityViewModel.statusBarColor.collectAsState()

                HabitTrackerApp(statusBarColor = statusBarColor)
            }
        }
    }
}
