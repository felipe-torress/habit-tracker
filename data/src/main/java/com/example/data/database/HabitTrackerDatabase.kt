package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.database.dao.GoalDao
import com.example.data.database.dao.HabitDao
import com.example.data.database.dao.HabitTaskDao
import com.example.data.database.dao.TaskWeeklyProgressDao
import com.example.data.database.dao.WeekDao
import com.example.data.database.model.GoalEntity
import com.example.data.database.model.GoalHabitTaskCrossRef
import com.example.data.database.model.HabitEntity
import com.example.data.database.model.HabitTaskEntity
import com.example.data.database.util.DayOfWeekListConverter
import com.example.data.database.util.HabitColorConverter
import com.example.data.database.util.LocalTimeConverter
import com.example.data.database.util.ZonedDateTimeConverter

@Database(
    entities = [
        GoalEntity::class,
        HabitTaskEntity::class,
        GoalHabitTaskCrossRef::class,
        HabitEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    ZonedDateTimeConverter::class,
    LocalTimeConverter::class,
    DayOfWeekListConverter::class,
    HabitColorConverter::class,
)
internal abstract class HabitTrackerDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao

    abstract fun habitDao(): HabitDao

    abstract fun habitTaskDao(): HabitTaskDao

    abstract fun weekDao(): WeekDao

    abstract fun taskWeeklyProgressDao(): TaskWeeklyProgressDao
}
