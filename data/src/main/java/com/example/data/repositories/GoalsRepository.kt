package com.example.data.repositories

import com.example.data.model.Goal
import kotlinx.coroutines.flow.Flow

interface GoalsRepository {

    /**
     * Creates a new [Goal]
     */
    suspend fun createGoal(goal: Goal)

    /**
     * Deletes a [Goal]
     */
    suspend fun deleteGoal(goalId: String)

    /**
     * Gets all [Goal]s from the user
     */
    fun getAllGoals(): Flow<List<Goal>>

    /**
     * Gets a [Goal] by it's id
     */
    fun getGoalById(goalId: String): Flow<Goal>

    /**
     * Gets all [Goal]s related to a given [Habit]
     * @param habitId id of the [Habit]
     */
    fun getGoalsFromHabit(habitId: String): Flow<List<Goal>>
}