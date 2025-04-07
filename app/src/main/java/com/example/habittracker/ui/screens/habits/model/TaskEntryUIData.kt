package com.example.habittracker.ui.screens.habits.model

import com.example.data.model.HabitTask
import timber.log.Timber
import java.time.DayOfWeek
import java.time.LocalTime

data class TaskEntryUIData(
    val name: String = "",
    val daysOfWeek: List<DayOfWeek> = emptyList(),
    val time: LocalTime? = null,
) {
    fun isValidToSave(): Boolean {
        return name.isNotBlank() && daysOfWeek.isNotEmpty() && time != null
    }

    fun addDayOfWeek(dayOfWeek: DayOfWeek): TaskEntryUIData {
        return copy(daysOfWeek = daysOfWeek + dayOfWeek)
    }

    fun removeDayOfWeek(dayOfWeek: DayOfWeek): TaskEntryUIData {
        return copy(daysOfWeek = daysOfWeek - dayOfWeek)
    }

    fun updateName(newName: String): TaskEntryUIData {
        return copy(name = newName)
    }

    fun updateTime(newTime: LocalTime?): TaskEntryUIData {
        return copy(time = newTime)
    }

    suspend fun getValidDataAndPerformAction(block: suspend (name: String, daysOfWeek: List<DayOfWeek>, time: LocalTime) -> Unit) {
        if (isValidToSave()) {
            block(name, daysOfWeek, time!!)
        } else Timber.e("Task entry is not valid to save: name=$name, daysOfWeek=$daysOfWeek, time=$time")
    }
}


fun HabitTask.toTaskEntryUIData(): TaskEntryUIData {
    return TaskEntryUIData(
        name = name,
        daysOfWeek = daysOfWeek,
        time = time,
    )
}
