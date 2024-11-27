package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.database.model.HabitEntity
import com.example.data.database.model.PopulatedHabit
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [Habit] and [HabitEntity] access
 */
@Dao
interface HabitDao {
    /**
     * Inserts a new Habit
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitEntity)

    /**
     * Deletes a Habit
     */
    @Query("DELETE FROM habits WHERE id = :habitId")
    suspend fun deleteHabit(habitId: String)

    /**
     * Fetches all the habits of a user
     */
    @Transaction
    @Query(value = "SELECT * FROM habits ORDER BY created_at DESC")
    fun getAllHabits(): Flow<List<PopulatedHabit>>

    /**
     * Fetches a habit by its id
     * @param habitId id of the Habit
     */
    @Transaction
    @Query(value = "SELECT * FROM habits WHERE id = :habitId")
    fun getHabitById(habitId: String): Flow<PopulatedHabit>
}
