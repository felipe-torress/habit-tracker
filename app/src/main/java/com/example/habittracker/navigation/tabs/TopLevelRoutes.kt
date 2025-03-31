package com.example.habittracker.navigation.tabs

import kotlinx.serialization.Serializable

sealed interface TopLevelRoutes {
    @Serializable
    data object HomeTab : TopLevelRoutes

    @Serializable
    data object HabitsTab : TopLevelRoutes

    @Serializable
    data object ProgressTab : TopLevelRoutes

    @Serializable
    data object ProfileTab : TopLevelRoutes
}
