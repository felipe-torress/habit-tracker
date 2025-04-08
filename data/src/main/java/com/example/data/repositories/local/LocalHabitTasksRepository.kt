package com.example.data.repositories.local

import com.example.data.database.dao.HabitTaskDao
import com.example.data.database.model.HabitTaskEntity
import com.example.data.mappers.asHabitTask
import com.example.data.mappers.asHabitTaskEntity
import com.example.data.model.HabitTask
import com.example.data.repositories.HabitTasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.ZonedDateTime
import javax.inject.Inject

class LocalHabitTasksRepository @Inject constructor(private val habitTaskDao: HabitTaskDao) : HabitTasksRepository {
    override suspend fun createHabitTask(habitTask: HabitTask) = habitTaskDao.insertHabitTask(habitTask.asHabitTaskEntity())

    override suspend fun createHabitTask(
        habitId: String,
        name: String,
        daysOfWeek: List<DayOfWeek>,
        time: LocalTime,
    ) {
        val habitTask = HabitTask(
            id = habitId,
            habitId = habitId,
            name = name,
            time = time,
            currentWeeklyCompletions = 0,
            requiredWeeklyCompletions = daysOfWeek.size,
            daysOfWeek = daysOfWeek,
            createdAt = ZonedDateTime.now(),
            updatedAt = ZonedDateTime.now(),
        )

        habitTaskDao.insertHabitTask(habitTask.asHabitTaskEntity())
    }

    override suspend fun deleteHabitTask(habitTaskId: String) =
        habitTaskDao.deleteHabitTask(
            habitTaskId,
        )

    override fun getAllHabitTasks(): Flow<List<HabitTask>> =
        habitTaskDao.getAllHabitTasks().map { habitTaskEntities ->
            habitTaskEntities.map(HabitTaskEntity::asHabitTask)
        }

    override fun getHabitTaskById(habitTaskId: String): Flow<HabitTask?> =
        habitTaskDao.getHabitTaskById(habitTaskId).map { habitTaskEntity ->
            habitTaskEntity.asHabitTask()
        }

    override suspend fun updateHabitTask(habitTaskId: String, name: String, daysOfWeek: List<DayOfWeek>, time: LocalTime) {
        val habitTask = getHabitTaskById(habitTaskId)
        habitTask.first()?.let { task ->
            val updatedHabitTask = task.copy(
                name = name,
                daysOfWeek = daysOfWeek,
                time = time,
                requiredWeeklyCompletions = daysOfWeek.size,
                updatedAt = ZonedDateTime.now(),
            )
            createHabitTask(updatedHabitTask)
        }
    }

    override fun getHabitTasksFromHabit(habitId: String): Flow<List<HabitTask>?> =
        habitTaskDao.getHabitTasksFromHabit(habitId).map { habitEntities ->
            habitEntities.map(HabitTaskEntity::asHabitTask)
        }
}
