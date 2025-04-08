package com.example.habittracker.ui.composables.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.habittracker.ui.composables.input.HabitTrackerTextField
import com.example.habittracker.ui.utils.testTags.TestTagState
import timber.log.Timber

@Composable
fun InputDialog(
    isVisible: Boolean,
    textFieldValue: String,
    resources: DialogResources,
    callbacks: DialogCallbacks,
    modifier: Modifier = Modifier,
) {
    if (resources is DialogResources.InputDialog && callbacks is DialogCallbacks.InputDialog) {
        GenericDialog(
            isVisible = isVisible,
            resources = resources,
            callbacks = callbacks,
            modifier = modifier,
        ) {
            HabitTrackerTextField(
                value = textFieldValue,
                placeholder = resources.textFieldPlaceholder,
                onValueChange = callbacks.onTextValueChange,
                modifier = Modifier.fillMaxWidth(),
                // TODO: Add test tag state
                testTagState = TestTagState("InputDialogTextField"),
            )
        }
    } else {
        Timber.e("Invalid DialogResources or DialogCallbacks for InputDialog")
    }
}

//region --- Preview ---
private data class InputDialogPreviewData(
    val title: String = "Renomear Hábito",
    val description: String? = null,
    val textFieldValue: String = "Exercitar Regularmente",
    val textFieldPlaceholder: String = "Nome do Hábito",
    val positiveTitle: String = "Salvar",
    val negativeTitle: String? = "Cancelar",
)

private class InputDialogPreviewParameterProvider : PreviewParameterProvider<InputDialogPreviewData> {
    override val values = sequenceOf(
        // Input dialog with no Description
        InputDialogPreviewData(),
        // Input dialog with Description
        InputDialogPreviewData(
            description = "Insira o novo nome do hábito",
        ),
    )
}

@Preview
@Composable
private fun InputDialogPreview(
    @PreviewParameter(InputDialogPreviewParameterProvider::class) previewData: InputDialogPreviewData,
) {
    InputDialog(
        isVisible = true,
        textFieldValue = previewData.textFieldValue,
        resources = DialogResources.InputDialog(
            title = previewData.title,
            description = previewData.description,
            textFieldPlaceholder = previewData.textFieldPlaceholder,
            positiveTitle = previewData.positiveTitle,
            negativeTitle = previewData.negativeTitle,
        ),
        callbacks = DialogCallbacks.InputDialog(
            onNegativeAction = {},
            onPositiveAction = {},
            onDismiss = {},
            onTextValueChange = {},
        ),
    )
}
//endregion
