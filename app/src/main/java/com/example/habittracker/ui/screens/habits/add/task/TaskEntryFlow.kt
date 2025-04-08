package com.example.habittracker.ui.screens.habits.add.task

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
sealed class TaskEntryFlow : Parcelable {

    @Serializable
    @Parcelize
    sealed class TemporaryTask : TaskEntryFlow(), Parcelable {

        @Serializable
        @Parcelize
        data object Add : TemporaryTask(), Parcelable

        @Serializable
        @Parcelize
        data class Edit(val taskId: String) : TemporaryTask(), Parcelable
    }

    @Serializable
    @Parcelize
    sealed class SavedTask : TaskEntryFlow(), Parcelable {
        @Serializable
        @Parcelize
        data class Add(val habitId: String) : SavedTask(), Parcelable

        @Serializable
        @Parcelize
        data class Edit(val taskId: String) : SavedTask(), Parcelable
    }
}
