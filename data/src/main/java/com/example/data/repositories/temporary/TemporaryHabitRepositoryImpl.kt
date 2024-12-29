package com.example.data.repositories.temporary

import com.example.data.model.Habit
import com.example.data.model.HabitColor
import com.example.data.model.HabitTask
import com.example.data.repositories.HabitsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.ZonedDateTime
import java.util.UUID
import javax.inject.Inject

interface TemporaryHabitRepository {
    val temporaryTasks: StateFlow<List<HabitTask>>

    suspend fun saveTemporaryHabit(
        name: String,
        color: HabitColor,
    )

    fun discardTemporaryHabit()
}

class TemporaryHabitRepositoryImpl @Inject constructor(
    private val habitsRepository: HabitsRepository
) : TemporaryHabitRepository {
    private val _temporaryTasks = MutableStateFlow<List<HabitTask>>(emptyList())
    override val temporaryTasks: StateFlow<List<HabitTask>> get() = _temporaryTasks.asStateFlow()

    override suspend fun saveTemporaryHabit(
        name: String,
        color: HabitColor,
    ) {
        val habit = Habit(
            id = UUID.randomUUID().toString(),
            name = name,
            color = color,
            tasks = temporaryTasks.value,
            goals = emptyList(),
            createdAt = ZonedDateTime.now(),
            updatedAt = ZonedDateTime.now(),
        )

        habitsRepository.createHabit(habit)
    }

    override fun discardTemporaryHabit() = _temporaryTasks.update { emptyList() }
}
