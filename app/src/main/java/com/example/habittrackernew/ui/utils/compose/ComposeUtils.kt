package com.example.habittrackernew.ui.utils.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun rememberInteractionsSource(): MutableInteractionSource = remember { MutableInteractionSource() }

fun Modifier.thenIf(
    predicate: Boolean,
    block: Modifier.() -> Modifier,
): Modifier =
    this.then(
        if (predicate) block() else Modifier,
    )
