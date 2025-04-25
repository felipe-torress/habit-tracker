package com.example.data.repositories.local

import com.example.data.database.dao.WeekDao
import com.example.data.database.model.WeekEntity
import com.example.data.database.model.WeekWithTasksProgress
import com.example.data.mappers.asWeek
import com.example.data.mappers.asWeekEntity
import com.example.data.model.Week
import com.example.data.repositories.WeeksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class LocalWeeksRepository @Inject constructor(private val weekDao: WeekDao) : WeeksRepository {

    override suspend fun insertWeek(week: Week) {
        weekDao.insertWeek(week.asWeekEntity())
    }

    override suspend fun deleteWeek(id: String) {
        weekDao.deleteWeek(id)
    }

    override fun getWeekContainingDate(date: LocalDate): Flow<Week?> {
        return weekDao.getWeekContainingDate(date).map(WeekEntity?::asWeek)
    }

    override fun getWeekByNumberAndYear(weekNumber: Int, year: Int): Flow<Week?> {
        return weekDao.getWeekByNumberAndYear(weekNumber = weekNumber, year = year).map(WeekEntity?::asWeek)
    }

    override fun getWeekWithUniqueTasks(date: LocalDate): Flow<Week?> {
        return weekDao.getWeekWithTasksProgress(date).map(WeekWithTasksProgress?::asWeek)
    }
}
