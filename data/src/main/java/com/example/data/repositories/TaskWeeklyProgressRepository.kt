package com.example.data.repositories

import com.example.data.model.TaskWeeklyProgress
import kotlinx.coroutines.flow.Flow

// TODO: Document it
interface TaskWeeklyProgressRepository {

    suspend fun insertTaskWeeklyProgress(taskWeeklyProgress: TaskWeeklyProgress)

    fun getTaskWeeklyProgressesById(id: String): Flow<TaskWeeklyProgress?>

    fun getTaskWeeklyProgressesByHabitTask(habitTaskId: String): Flow<List<TaskWeeklyProgress>>

    fun getTaskWeeklyProgressesByWeek(weekId: String): Flow<List<TaskWeeklyProgress>>
}
