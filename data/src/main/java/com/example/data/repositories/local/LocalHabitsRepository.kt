package com.example.data.repositories.local

import com.example.data.database.dao.HabitDao
import com.example.data.database.model.PopulatedHabit
import com.example.data.database.model.asHabit
import com.example.data.mappers.asHabitEntity
import com.example.data.model.Habit
import com.example.data.repositories.HabitsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalHabitsRepository @Inject constructor(private val habitDao: HabitDao) : HabitsRepository {
    override suspend fun createHabit(habit: Habit) = habitDao.insertHabit(habit.asHabitEntity())

    override suspend fun deleteHabit(habitId: String) = habitDao.deleteHabit(habitId)

    override fun getAllHabits(): Flow<List<Habit>> =
        habitDao.getAllHabits().map { habitEntities ->
            habitEntities.map(PopulatedHabit::asHabit)
        }

    override fun getHabitById(habitId: String): Flow<Habit> =
        habitDao.getHabitById(habitId).map { habitEntity ->
            habitEntity.asHabit()
        }
}
