package com.example.data.repositories

import com.example.data.model.Week
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface WeeksRepository {

    /**
     * Create a new [Week]
     */
    suspend fun insertWeek(week: Week)

    /**
     * Delete a [Week]
     */
    suspend fun deleteWeek(id: String)

    /**
     * Retrieves a [Week] by date.
     */
    fun getWeekContainingDate(date: LocalDate): Flow<Week?>

    /**
     * Retrieves a [Week] by its week number and year.
     */
    fun getWeekByNumberAndYear(weekNumber: Int, year: Int): Flow<Week?>

    /**
     * Retrieves a [Week] populated with Unique Tasks by date.
     */
    fun getWeekWithUniqueTasks(date: LocalDate): Flow<Week?>

}
