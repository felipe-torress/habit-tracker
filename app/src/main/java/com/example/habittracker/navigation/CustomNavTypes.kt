package com.example.habittracker.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.habittracker.ui.screens.habits.add.task.TaskEntryFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {

    val TaskEntryFlowType = object : NavType<TaskEntryFlow>(isNullableAllowed = false) {
        override fun put(bundle: Bundle, key: String, value: TaskEntryFlow) {
            bundle.putParcelable(key, value)
        }
        override fun get(bundle: Bundle, key: String): TaskEntryFlow {
            return bundle.getParcelable(key)!!
        }

        override fun serializeAsValue(value: TaskEntryFlow): String {
            // Serialized values must always be Uri encoded
            return Uri.encode(Json.encodeToString(value))
        }

        override fun parseValue(value: String): TaskEntryFlow {
            // Navigation takes care of decoding the string
            // before passing it to parseValue()
            return Json.decodeFromString<TaskEntryFlow>(value)
        }
    }
}
