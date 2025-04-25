package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.database.model.TaskWeeklyProgressEntity
import kotlinx.coroutines.flow.Flow

// TODO: Document it

@Dao
interface TaskWeeklyProgressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskWeeklyProgress(taskWeeklyProgress: TaskWeeklyProgressEntity)

    @Query("SELECT * FROM week_progress WHERE id = :id")
    fun getTaskWeeklyProgressesById(id: String): Flow<TaskWeeklyProgressEntity?>

    @Transaction
    @Query("SELECT * FROM week_progress WHERE habit_task_id = :habitTaskId")
    fun getTaskWeeklyProgressesByHabitTask(habitTaskId: String): Flow<List<TaskWeeklyProgressEntity>>

    @Transaction
    @Query("SELECT * FROM week_progress WHERE week_id = :weekId")
    fun getTaskWeeklyProgressesByWeek(weekId: String): Flow<List<TaskWeeklyProgressEntity>>

}
