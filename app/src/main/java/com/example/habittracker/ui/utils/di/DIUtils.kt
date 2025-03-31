package com.example.habittracker.ui.utils.di

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner

@Composable
inline fun <reified VM : ViewModel> activityHiltViewModel(): VM {
    val activity = LocalContext.current as Activity
    return hiltViewModel(viewModelStoreOwner = activity as ViewModelStoreOwner)
}
