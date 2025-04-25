package com.example.data.di

import com.example.data.database.HabitTrackerDatabase
import com.example.data.database.dao.GoalDao
import com.example.data.database.dao.HabitDao
import com.example.data.database.dao.HabitTaskDao
import com.example.data.database.dao.TaskWeeklyProgressDao
import com.example.data.database.dao.WeekDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesGoalDao(database: HabitTrackerDatabase): GoalDao = database.goalDao()

    @Provides
    fun providesHabitDao(database: HabitTrackerDatabase): HabitDao = database.habitDao()

    @Provides
    fun providesHabitTaskDao(database: HabitTrackerDatabase): HabitTaskDao = database.habitTaskDao()

    @Provides
    fun providesTaskWeeklyProgressDao(database: HabitTrackerDatabase): TaskWeeklyProgressDao =
        database.taskWeeklyProgressDao()

    @Provides
    fun providesWeekDao(database: HabitTrackerDatabase): WeekDao = database.weekDao()
}
