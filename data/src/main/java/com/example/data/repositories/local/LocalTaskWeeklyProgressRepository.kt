package com.example.data.repositories.local

import com.example.data.database.dao.TaskWeeklyProgressDao
import com.example.data.database.model.TaskWeeklyProgressEntity
import com.example.data.mappers.asTaskWeeklyProgress
import com.example.data.mappers.asTaskWeeklyProgressEntity
import com.example.data.model.TaskWeeklyProgress
import com.example.data.repositories.TaskWeeklyProgressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalTaskWeeklyProgressRepository(private val taskWeeklyProgressDao: TaskWeeklyProgressDao) :
    TaskWeeklyProgressRepository {

    override suspend fun insertTaskWeeklyProgress(taskWeeklyProgress: TaskWeeklyProgress) {
        taskWeeklyProgressDao.insertTaskWeeklyProgress(taskWeeklyProgress.asTaskWeeklyProgressEntity())
    }

    override fun getTaskWeeklyProgressesById(id: String): Flow<TaskWeeklyProgress?> {
        return taskWeeklyProgressDao.getTaskWeeklyProgressesById(id).map(TaskWeeklyProgressEntity?::asTaskWeeklyProgress)
    }

    override fun getTaskWeeklyProgressesByHabitTask(habitTaskId: String): Flow<List<TaskWeeklyProgress>> {
        return taskWeeklyProgressDao.getTaskWeeklyProgressesByHabitTask(habitTaskId).map { weeklyProgresses ->
            weeklyProgresses.map(TaskWeeklyProgressEntity::asTaskWeeklyProgress)
        }
    }

    override fun getTaskWeeklyProgressesByWeek(weekId: String): Flow<List<TaskWeeklyProgress>> {
        return taskWeeklyProgressDao.getTaskWeeklyProgressesByWeek(weekId).map { weeklyProgresses ->
            weeklyProgresses.map(TaskWeeklyProgressEntity::asTaskWeeklyProgress)
        }
    }
}
