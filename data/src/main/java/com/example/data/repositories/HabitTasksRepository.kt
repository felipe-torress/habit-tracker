package com.example.data.repositories

import com.example.data.model.HabitTask
import kotlinx.coroutines.flow.Flow
import java.time.DayOfWeek
import java.time.LocalTime

interface HabitTasksRepository {
    /**
     * Creates a new [HabitTask]
     */
    suspend fun createHabitTask(habitTask: HabitTask)

    /**
     * Deletes a [HabitTask]
     */
    suspend fun deleteHabitTask(habitTaskId: String)

    /**
     * Gets all [HabitTask]s from the user
     */
    fun getAllHabitTasks(): Flow<List<HabitTask>>

    /**
     * Gets a [HabitTask] by it's id
     */
    fun getHabitTaskById(habitTaskId: String): Flow<HabitTask?>

    /**
     * Updates a [HabitTask] with the given id
     */
    suspend fun updateHabitTask(
        habitTaskId: String,
        name: String,
        daysOfWeek: List<DayOfWeek>,
        time: LocalTime,
    )

    /**
     * Gets all [HabitTask]s related to a given [Habit]
     */
    fun getHabitTasksFromHabit(habitId: String): Flow<List<HabitTask>?>
}
