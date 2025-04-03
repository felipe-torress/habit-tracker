package com.example.habittracker.ui.screens.habits.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.habittracker.R
import com.example.habittracker.ui.composables.dialogs.CommonDialogResources
import com.example.habittracker.ui.composables.dialogs.CommonDialogResources.genericDialog
import com.example.habittracker.ui.composables.dialogs.DialogResources

sealed interface HabitDetailsDialogType {
    sealed interface BasicDialog : HabitDetailsDialogType {
        data object DeleteHabit : BasicDialog

        data object LoadHabitFailed : BasicDialog

        data object Generic : BasicDialog
    }

    sealed interface InputDialog : HabitDetailsDialogType {
        data object RenameHabit : InputDialog
    }

    companion object {
        @Composable
        private fun loadHabitFailedDialog(): DialogResources.BasicDialog {
            return CommonDialogResources.genericTryAgainDialog().copy(
                title = stringResource(R.string.habit_details_screen_load_habit_failed_dialog_title),
                description = stringResource(R.string.habit_details_screen_load_habit_failed_dialog_description),
            )
        }

        @Composable
        private fun deleteHabitDialog(): DialogResources.BasicDialog {
            return CommonDialogResources.deleteDialog().copy(
                title = stringResource(R.string.habit_details_screen_delete_habit_dialog_title),
                description = stringResource(R.string.habit_details_screen_delete_habit_dialog_description),
            )
        }

        @Composable
        private fun renameHabitDialog(): DialogResources.InputDialog {
            return DialogResources.InputDialog(
                title = stringResource(R.string.rename_habit_dialog_title),
                positiveTitle = stringResource(R.string.dialog_text_save),
                negativeTitle = stringResource(R.string.dialog_text_cancel),
                textFieldPlaceholder = stringResource(R.string.rename_habit_dialog_placeholder),
            )
        }

        @Composable
        fun getDialogResources(dialogType: HabitDetailsDialogType): DialogResources {
            return when (dialogType) {
                BasicDialog.Generic -> genericDialog()
                BasicDialog.DeleteHabit -> deleteHabitDialog()
                BasicDialog.LoadHabitFailed -> loadHabitFailedDialog()
                InputDialog.RenameHabit -> renameHabitDialog()
            }
        }
    }
}
