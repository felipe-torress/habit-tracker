package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.HabitTrackerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providersHabitTrackerDatabase(
        @ApplicationContext context: Context
    ): HabitTrackerDatabase = Room.databaseBuilder(
        context = context,
        klass = HabitTrackerDatabase::class.java,
        name = "habit-tracker-database"
    ).build()
}