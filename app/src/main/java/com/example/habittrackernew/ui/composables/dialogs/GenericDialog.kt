package com.example.habittrackernew.ui.composables.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.theme.HabitTrackerTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericDialog(
    isVisible: Boolean,
    resources: GenericDialogResources,
    callbacks: GenericDialogCallbacks,
    modifier: Modifier = Modifier,
) {
    if (isVisible) {
        BasicAlertDialog(
            onDismissRequest = callbacks.onDismiss,
            modifier = modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(16.dp))
                .background(HabitTrackerColors.backgroundColor)
                .padding(16.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Title(title = resources.title)
                Description(description = resources.description)
                Buttons(
                    positiveTitle = resources.positiveTitle,
                    negativeTitle = resources.negativeTitle,
                    onPositiveAction = callbacks.onPositiveAction,
                    onNegativeAction = callbacks.onNegativeAction,
                )
            }
        }
    }
}

@Composable
private fun Title(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = HabitTrackerTypography.subtitle1,
        color = HabitTrackerColors.textColor,
        textAlign = TextAlign.Left,
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
private fun Description(
    description: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = description,
        style = HabitTrackerTypography.bodySmall,
        color = HabitTrackerColors.textColor,
        textAlign = TextAlign.Left,
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
private fun Buttons(
    positiveTitle: String,
    negativeTitle: String?,
    onPositiveAction: () -> Unit,
    onNegativeAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
        modifier = modifier.fillMaxWidth(),
    ) {
        negativeTitle?.let { negativeTitle ->
            NegativeButton(
                negativeTitle = negativeTitle,
                onClick = onNegativeAction,
            )
        }

        PositiveButton(
            positiveTitle = positiveTitle,
            onClick = onPositiveAction,
        )
    }
}

@Composable
private fun NegativeButton(
    negativeTitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.heightIn(48.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.textButtonColors(
            contentColor = HabitTrackerColors.darkGrey300,
            disabledContentColor = HabitTrackerColors.darkGrey300,
        ),
    ) {
        Text(
            text = negativeTitle,
            style = HabitTrackerTypography.bodyLarge,
            color = HabitTrackerColors.darkGrey300,
        )
    }
}

@Composable
private fun PositiveButton(
    positiveTitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier.heightIn(48.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = HabitTrackerColors.green700,
            contentColor = HabitTrackerColors.green50,
            disabledContainerColor = HabitTrackerColors.darkGrey50,
            disabledContentColor = HabitTrackerColors.darkGrey300,
        ),
    ) {
        Text(
            text = positiveTitle,
            style = HabitTrackerTypography.bodyLarge,
            textAlign = TextAlign.Center,
            color = HabitTrackerColors.green50,
        )
    }
}

//region --- UI Objects ---
data class GenericDialogResources(
    val title: String,
    val description: String,
    val positiveTitle: String,
    val negativeTitle: String? = null,
)

data class GenericDialogCallbacks(
    val onPositiveAction: () -> Unit = {},
    val onDismiss: () -> Unit = {},
    val onNegativeAction: () -> Unit = {},
)
//endregion

//region --- Preview ---
private data class GenericDialogPreviewData(
    val title: String = "Ops, algo deu errado",
    val description: String = "Sentimos muito, tivemos um problema :(",
    val positiveTitle: String = "OK",
    val negativeTitle: String? = null,
)

private class GenericDialogPreviewParameterProvider : PreviewParameterProvider<GenericDialogPreviewData> {
    override val values = sequenceOf(
        // No Negative Action
        GenericDialogPreviewData(),
        // With Negative Action
        GenericDialogPreviewData(
            positiveTitle = "Tentar de novo",
            negativeTitle = "Cancelar",
        ),
    )
}

@Preview
@Composable
private fun GenericDialogPreview(
    @PreviewParameter(GenericDialogPreviewParameterProvider::class) previewData: GenericDialogPreviewData,
) {
    GenericDialog(
        isVisible = true,
        resources = GenericDialogResources(
            title = previewData.title,
            description = previewData.description,
            positiveTitle = previewData.positiveTitle,
            negativeTitle = previewData.negativeTitle,
        ),
        callbacks = GenericDialogCallbacks(
            onNegativeAction = {},
            onPositiveAction = {},
            onDismiss = {},
        ),
    )
}
//endregion
