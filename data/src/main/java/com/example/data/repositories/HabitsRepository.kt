package com.example.data.repositories

import com.example.data.model.Habit
import kotlinx.coroutines.flow.Flow

interface HabitsRepository {
    /**
     * Creates a new [Habit]
     */
    suspend fun createHabit(habit: Habit)

    /**
     * Deletes a [Habit]
     */
    suspend fun deleteHabit(habitId: String)

    /**
     * Gets all [Habit]s from the user
     */
    fun getAllHabits(): Flow<List<Habit>>

    /**
     * Gets a [Habit] by it's id
     */
    fun getHabitById(habitId: String): Flow<Habit?>
}
