package com.example.habittracker.ui.composables.dialogs

sealed class DialogCallbacks {
    abstract val onPositiveAction: () -> Unit
    abstract val onDismiss: () -> Unit
    abstract val onNegativeAction: () -> Unit

    data class BasicDialog(
        override val onPositiveAction: () -> Unit = {},
        override val onDismiss: () -> Unit = {},
        override val onNegativeAction: () -> Unit = {},
    ) : DialogCallbacks()

    data class InputDialog(
        override val onPositiveAction: () -> Unit = {},
        override val onDismiss: () -> Unit = {},
        override val onNegativeAction: () -> Unit = {},
        val onTextValueChange: (newValue: String) -> Unit = {},
    ) : DialogCallbacks()

    fun isBasicDialog() = this is BasicDialog
    fun isInputDialog() = this is InputDialog
}
