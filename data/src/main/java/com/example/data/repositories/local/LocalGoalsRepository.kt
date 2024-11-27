package com.example.data.repositories.local

import com.example.data.database.dao.GoalDao
import com.example.data.database.model.PopulatedGoal
import com.example.data.database.model.asGoal
import com.example.data.mappers.asGoalEntity
import com.example.data.model.Goal
import com.example.data.repositories.GoalsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalGoalsRepository @Inject constructor(
    private val goalDao: GoalDao
) : GoalsRepository {

    override suspend fun createGoal(goal: Goal) = goalDao.insertGoal(goal.asGoalEntity())

    override suspend fun deleteGoal(goalId: String) = goalDao.deleteGoal(goalId)

    override fun getAllGoals(): Flow<List<Goal>> = goalDao.getAllGoals().map { goalEntities ->
        goalEntities.map(PopulatedGoal::asGoal)
    }

    override fun getGoalById(goalId: String): Flow<Goal> = goalDao.getGoalById(goalId).map { goalEntity ->
        goalEntity.asGoal()
    }

    override fun getGoalsFromHabit(habitId: String): Flow<List<Goal>> = goalDao.getGoalsFromHabit(habitId).map { habitEntities ->
        habitEntities.map(PopulatedGoal::asGoal)
    }
}