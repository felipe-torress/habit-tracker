package com.example.habittracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class HabitTrackerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // TODO: Remove this in production
        Timber.plant(Timber.DebugTree())
    }
}
