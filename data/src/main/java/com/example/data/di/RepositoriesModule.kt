package com.example.data.di

import com.example.data.repositories.GoalsRepository
import com.example.data.repositories.HabitTasksRepository
import com.example.data.repositories.HabitsRepository
import com.example.data.repositories.local.LocalGoalsRepository
import com.example.data.repositories.local.LocalHabitTasksRepository
import com.example.data.repositories.local.LocalHabitsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Binds
    internal abstract fun bindsHabitsRepository(habitsRepository: LocalHabitsRepository): HabitsRepository

    @Binds
    internal abstract fun bindsGoalsRepository(goalsRepository: LocalGoalsRepository): GoalsRepository

    @Binds
    internal abstract fun bindsHabitTasksRepository(habitTasksRepository: LocalHabitTasksRepository): HabitTasksRepository
}
