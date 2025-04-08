package com.example.data.di

import com.example.data.repositories.GoalsRepository
import com.example.data.repositories.HabitTasksRepository
import com.example.data.repositories.HabitsRepository
import com.example.data.repositories.local.LocalGoalsRepository
import com.example.data.repositories.local.LocalHabitTasksRepository
import com.example.data.repositories.local.LocalHabitsRepository
import com.example.data.repositories.temporary.TemporaryHabitRepository
import com.example.data.repositories.temporary.TemporaryHabitRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Binds
    @Singleton
    internal abstract fun bindsHabitsRepository(implementation: LocalHabitsRepository): HabitsRepository

    @Binds
    @Singleton
    internal abstract fun bindsGoalsRepository(implementation: LocalGoalsRepository): GoalsRepository

    @Binds
    @Singleton
    internal abstract fun bindsHabitTasksRepository(implementation: LocalHabitTasksRepository): HabitTasksRepository

    @Binds
    @Singleton
    internal abstract fun bindsTemporaryHabitRepository(
        implementation: TemporaryHabitRepositoryImpl,
    ): TemporaryHabitRepository
}
