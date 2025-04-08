package com.example.data.repositories.local

import com.example.data.database.dao.GoalDao
import com.example.data.database.dao.HabitDao
import com.example.data.database.dao.HabitTaskDao
import com.example.data.database.model.PopulatedHabit
import com.example.data.database.model.asHabit
import com.example.data.mappers.asGoalEntity
import com.example.data.mappers.asHabitEntity
import com.example.data.mappers.asHabitTaskEntity
import com.example.data.model.Goal
import com.example.data.model.Habit
import com.example.data.model.HabitTask
import com.example.data.repositories.HabitsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalHabitsRepository @Inject constructor(
    private val habitDao: HabitDao,
    private val goalDao: GoalDao,
    private val habitTaskDao: HabitTaskDao,
) : HabitsRepository {
    override suspend fun upsertHabit(habit: Habit) {
        habitDao.insertHabit(habit.asHabitEntity())
        goalDao.insertGoals(habit.goals.map(Goal::asGoalEntity))
        habitTaskDao.insertHabitTasks(habit.tasks.map(HabitTask::asHabitTaskEntity))
    }

    override suspend fun deleteHabit(habitId: String) = habitDao.deleteHabit(habitId)

    override fun getAllHabits(): Flow<List<Habit>> =
        habitDao.getAllHabits().map { habitEntities ->
            habitEntities.map(PopulatedHabit::asHabit)
        }

    override fun getHabitById(habitId: String): Flow<Habit?> {
        return habitDao.getHabitById(habitId).map { habitEntity ->
            habitEntity?.asHabit()
        }
    }
}
