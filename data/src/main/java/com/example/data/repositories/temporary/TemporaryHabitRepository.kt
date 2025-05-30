package com.example.data.repositories.temporary

import com.example.data.model.Habit
import com.example.data.model.HabitColor
import com.example.data.model.HabitTask
import com.example.data.repositories.HabitsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.ZonedDateTime
import java.util.UUID
import javax.inject.Inject

interface TemporaryHabitRepository {
    val temporaryTasks: StateFlow<List<HabitTask>>

    suspend fun createTask(
        name: String,
        daysOfWeek: List<DayOfWeek>,
        time: LocalTime,
    )

    suspend fun editTask(
        taskId: String,
        name: String,
        daysOfWeek: List<DayOfWeek>,
        time: LocalTime,
    )

    suspend fun deleteTask(taskId: String)

    suspend fun saveTemporaryHabit(
        name: String,
        color: HabitColor,
    )

    fun discardTemporaryHabit()
}

class TemporaryHabitRepositoryImpl @Inject constructor(
    private val habitsRepository: HabitsRepository,
) : TemporaryHabitRepository {
    private val _temporaryTasks = MutableStateFlow<List<HabitTask>>(emptyList())
    override val temporaryTasks: StateFlow<List<HabitTask>> get() = _temporaryTasks.asStateFlow()

    override suspend fun editTask(
        taskId: String,
        name: String,
        daysOfWeek: List<DayOfWeek>,
        time: LocalTime,
    ) {
        temporaryTasks.value.find {
            it.id == taskId
        }?.let { task ->
            val updatedTask = task.copy(
                name = name.trim(),
                daysOfWeek = daysOfWeek,
                time = time,
                requiredWeeklyCompletions = daysOfWeek.size,
                updatedAt = ZonedDateTime.now(),
            )

            _temporaryTasks.update { temporaryTasks ->
                temporaryTasks.map { task ->
                    if (task.id == taskId) updatedTask else task
                }
            }
        }
    }

    override suspend fun createTask(
        name: String,
        daysOfWeek: List<DayOfWeek>,
        time: LocalTime,
    ) {
        val task = HabitTask(
            id = UUID.randomUUID().toString(),
            habitId = "",
            name = name.trim(),
            daysOfWeek = daysOfWeek,
            time = time,
            currentWeeklyCompletions = 0,
            requiredWeeklyCompletions = daysOfWeek.size,
            createdAt = ZonedDateTime.now(),
            updatedAt = ZonedDateTime.now(),
        )

        _temporaryTasks.update { temporaryTasks -> temporaryTasks + task }
    }

    override suspend fun deleteTask(taskId: String) {
        _temporaryTasks.update { temporaryTasks ->
            temporaryTasks.filterNot { it.id == taskId }
        }
    }

    override suspend fun saveTemporaryHabit(
        name: String,
        color: HabitColor,
    ) {
        val habitId = UUID.randomUUID().toString()
        val createdAt = ZonedDateTime.now()

        val tasks = temporaryTasks.value.map { task ->
            task.copy(
                habitId = habitId,
                createdAt = createdAt,
                updatedAt = createdAt,
            )
        }

        val habit = Habit(
            id = habitId,
            name = name.trim(),
            color = color,
            tasks = tasks,
            goals = emptyList(),
            createdAt = ZonedDateTime.now(),
            updatedAt = ZonedDateTime.now(),
        )

        habitsRepository.upsertHabit(habit)
        discardTemporaryHabit()
    }

    override fun discardTemporaryHabit() {
        _temporaryTasks.update { emptyList() }
    }
}
