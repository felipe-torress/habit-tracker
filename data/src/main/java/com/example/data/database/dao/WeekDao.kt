package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.database.model.WeekEntity
import com.example.data.database.model.WeekWithTasksProgress
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface WeekDao {

    /**
     * Inserts a new week.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeek(week: WeekEntity)

    /**
     * Deletes a week by it's id.
     */
    @Query("DELETE FROM weeks WHERE id = :id")
    suspend fun deleteWeek(id: String)

    /**
     * Retrieves a week by date.
     */
    @Query("SELECT * FROM weeks WHERE :date BETWEEN startDate AND endDate")
    fun getWeekContainingDate(date: LocalDate): Flow<WeekEntity?>

    /**
     * Retrieves a week by its week number and year.
     */
    @Query("SELECT * FROM weeks WHERE weekNumber = :weekNumber AND year = :year")
    fun getWeekByNumberAndYear(weekNumber: Int, year: Int): Flow<WeekEntity?>

    /**
     * Retrieves a week populated with Unique Tasks by date.
     */
    @Transaction
    @Query("SELECT * FROM weeks WHERE :date BETWEEN startDate AND endDate")
    fun getWeekWithTasksProgress(date: LocalDate): Flow<WeekWithTasksProgress?>
}
