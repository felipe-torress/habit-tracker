package com.example.habittrackernew.navigation

import androidx.annotation.DrawableRes
import com.example.habittrackernew.R

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val titleResId: Int,
    @DrawableRes val iconResId: Int,
) {
    HOME(
        titleResId = R.string.home_tab_title,
        iconResId = R.drawable.ic_home_24dp,
    ),
    HABITS(
        titleResId = R.string.habits_tab_title,
        iconResId = R.drawable.ic_habits_24dp,
    ),
    PROGRESS(
        titleResId = R.string.progress_tab_title,
        iconResId = R.drawable.ic_progress_24dp,
    ),
    PROFILE(
        titleResId = R.string.profile_tab_title,
        iconResId = R.drawable.ic_profile_24dp,
    ),
}
