package com.example.habittracker.ui.screens.habits.add.task

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.habittracker.R
import com.example.habittracker.ui.composables.dialogs.CommonDialogResources
import com.example.habittracker.ui.composables.dialogs.DialogResources

sealed interface TaskEntryDialogType {
    sealed class Confirmation : TaskEntryDialogType {
        data object Delete : Confirmation()
    }

    companion object {
        @Composable
        fun deleteTaskDialog(): DialogResources.BasicDialog {
            return CommonDialogResources.deleteDialog().copy(
                title = stringResource(R.string.task_entry_screen_delete_task_button_title),
                description = stringResource(R.string.task_entry_screen_delete_task_dialog_description),
            )
        }
    }

    @Composable
    fun getDialogResources(dialogType: TaskEntryDialogType): DialogResources {
        return when (dialogType) {
            Confirmation.Delete -> deleteTaskDialog()
        }
    }
}
