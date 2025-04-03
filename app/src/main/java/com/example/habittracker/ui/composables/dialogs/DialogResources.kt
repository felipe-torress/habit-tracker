package com.example.habittracker.ui.composables.dialogs

sealed class DialogResources {
    abstract val title: String
    abstract val positiveTitle: String
    abstract val description: String?
    abstract val negativeTitle: String?

    data class BasicDialog(
        override val title: String,
        override val positiveTitle: String,
        override val description: String? = null,
        override val negativeTitle: String? = null,
    ) : DialogResources()

    data class InputDialog(
        override val title: String,
        override val positiveTitle: String,
        override val description: String? = null,
        override val negativeTitle: String? = null,
        val textFieldPlaceholder: String? = null,
    ) : DialogResources()

    fun isBasicDialog() = this is BasicDialog

    fun isInputDialog() = this is InputDialog
}
