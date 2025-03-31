package com.example.habittracker.ui.screens.habits.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.habittracker.R
import com.example.habittracker.ui.composables.dialogs.DialogResources
import com.example.habittracker.ui.composables.dialogs.DialogResources.genericDialog
import com.example.habittracker.ui.composables.dialogs.GenericDialogResources

sealed interface HabitDetailsDialogType {
    data object DeleteHabit : HabitDetailsDialogType

    data object LoadHabitFailed : HabitDetailsDialogType

    data object Generic : HabitDetailsDialogType

    companion object {
        @Composable
        private fun loadHabitFailedDialog() =
            DialogResources.genericTryAgainDialog().copy(
                title = stringResource(R.string.habit_details_screen_load_habit_failed_dialog_title),
                description = stringResource(R.string.habit_details_screen_load_habit_failed_dialog_description),
            )

        @Composable
        private fun deleteHabitDialog() =
            DialogResources.deleteDialog().copy(
                title = stringResource(R.string.habit_details_screen_delete_habit_dialog_title),
                description = stringResource(R.string.habit_details_screen_delete_habit_dialog_description),
            )

        @Composable
        fun getDialogResources(dialogType: HabitDetailsDialogType): GenericDialogResources {
            return when (dialogType) {
                Generic -> genericDialog()
                DeleteHabit -> deleteHabitDialog()
                LoadHabitFailed -> loadHabitFailedDialog()
            }
        }
    }
}
