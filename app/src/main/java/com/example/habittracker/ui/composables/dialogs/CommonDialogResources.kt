package com.example.habittracker.ui.composables.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.habittracker.R

object CommonDialogResources {
    @Composable
    fun genericDialog() =
        DialogResources.BasicDialog(
            title = stringResource(R.string.dialog_generic_title),
            description = stringResource(R.string.dialog_generic_description),
            positiveTitle = stringResource(R.string.dialog_text_ok),
            negativeTitle = stringResource(R.string.dialog_text_cancel),
        )

    @Composable
    fun genericTryAgainDialog() =
        DialogResources.BasicDialog(
            title = stringResource(R.string.dialog_generic_title),
            description = stringResource(R.string.dialog_generic_try_again_description),
            positiveTitle = stringResource(R.string.dialog_text_try_again),
            negativeTitle = stringResource(R.string.dialog_text_cancel),
        )

    @Composable
    fun deleteDialog() =
        DialogResources.BasicDialog(
            title = stringResource(R.string.dialog_delete_generic_title),
            description = stringResource(R.string.dialog_delete_generic_description),
            positiveTitle = stringResource(R.string.dialog_text_delete),
            negativeTitle = stringResource(R.string.dialog_text_cancel),
        )
}
