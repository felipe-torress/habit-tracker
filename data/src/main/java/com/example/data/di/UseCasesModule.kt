package com.example.data.di

import com.example.data.repositories.HabitsRepository
import com.example.data.usecases.GetWeekTasksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object UseCasesModule {
    @Provides
    fun providesGetPendingTasksUseCase(habitsRepository: HabitsRepository): GetWeekTasksUseCase =
        GetWeekTasksUseCase(habitsRepository)
}
