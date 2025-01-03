package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.model.GoalEntity
import com.example.data.database.model.PopulatedGoal
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [Goal] and [GoalEntity] access
 */
@Dao
interface GoalDao {

    /**
     * Inserts a list of Goals
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoals(goals: List<GoalEntity>)

    /**
     * Inserts a new Goal
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: GoalEntity)

    /**
     * Deletes a Goal
     */
    @Query("DELETE FROM goals WHERE id = :goalId")
    suspend fun deleteGoal(goalId: String)

    /**
     * Fetches all the goals of the user by descending order of creationDate
     */
    @Query(value = "SELECT * FROM goals ORDER BY created_at DESC")
    fun getAllGoals(): Flow<List<PopulatedGoal>>

    /**
     * Fetches a goal by its id
     */
    @Query(value = "SELECT * FROM goals WHERE id = :goalId")
    fun getGoalById(goalId: String): Flow<PopulatedGoal>

    /**
     * Fetches all the goals of the user related to a given Habit
     */
    @Query(value = "SELECT * FROM goals WHERE habit_id = :habitId")
    fun getGoalsFromHabit(habitId: String): Flow<List<PopulatedGoal>>
}
