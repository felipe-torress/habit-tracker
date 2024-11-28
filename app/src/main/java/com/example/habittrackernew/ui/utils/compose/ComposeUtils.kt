package com.example.habittrackernew.ui.utils.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberInteractionsSource(): MutableInteractionSource = remember { MutableInteractionSource() }
