package com.example.habittrackernew.ui.screens.habits.details.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittrackernew.ui.screens.habits.model.ColorUI
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.theme.HabitTrackerTypography

fun LazyListScope.habitDetailsSection(
    @StringRes titleResId: Int,
    @StringRes descriptionResId: Int,
    color: ColorUI,
    isFirstSection: Boolean,
    content: LazyListScope.() -> Unit
) {
    title(
        titleResId = titleResId,
        color = color,
        isFirstSection = isFirstSection
    )

    description(descriptionResId = descriptionResId)

    content()
}

private fun LazyListScope.title(
    @StringRes titleResId: Int,
    color: ColorUI,
    isFirstSection: Boolean
) {
    item {
        val textColor = remember {
            when (color) {
                ColorUI.BLUE -> HabitTrackerColors.blue700
                ColorUI.GREEN -> HabitTrackerColors.green700
                ColorUI.PURPLE -> HabitTrackerColors.purple700
            }
        }

        Text(
            text = stringResource(titleResId),
            style = HabitTrackerTypography.subtitle1.copy(fontSize = 20.sp),
            color = textColor,
            modifier = Modifier.padding(top = if (isFirstSection) 0.dp else 24.dp)
        )
    }
}

private fun LazyListScope.description(@StringRes descriptionResId: Int) {
    item {
        Text(
            text = stringResource(descriptionResId),
            style = HabitTrackerTypography.bodySmall,
            color = HabitTrackerColors.textColor,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
