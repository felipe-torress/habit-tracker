package com.example.habittrackernew.ui.composables.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.theme.HabitTrackerTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    hasNavigationIcon: Boolean = false,
    hasActionButton: Boolean = false,
    navigationIcon: @Composable (onClick: () -> Unit) -> Unit = { onClick -> CollapseIcon(onClick) },
    onNavigationIconClick: () -> Unit = {},
    actionButton: @Composable RowScope.(onClick: () -> Unit) -> Unit = { onClick -> AddButton(onClick) },
    onActionButtonClick: () -> Unit = {},
) {
    TopAppBar(
        title = { Title(title) },
        navigationIcon = { if (hasNavigationIcon) navigationIcon(onNavigationIconClick) },
        actions = { if (hasActionButton) actionButton(onActionButtonClick) },
        scrollBehavior = pinnedScrollBehavior(),
        colors = TopAppBarColors(
            containerColor = HabitTrackerColors.backgroundColor,
            scrolledContainerColor = HabitTrackerColors.backgroundColor,
            navigationIconContentColor = HabitTrackerColors.green500,
            titleContentColor = HabitTrackerColors.textColor,
            actionIconContentColor = HabitTrackerColors.green500
        ),
        modifier = Modifier.background(HabitTrackerColors.backgroundColor)
    )
}

@Composable
private fun Title(title: String) {
    Text(
        text = title,
        style = HabitTrackerTypography.headline4,
        color = HabitTrackerColors.textColor
    )
}

@Composable
private fun CollapseIcon(
    onClick: () -> Unit,
    color: Color = HabitTrackerColors.blue800
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(R.drawable.ic_minimize_24dp),
            contentDescription = "Collapse Icon",
            tint = color,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun AddButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = buttonColors(
            containerColor = HabitTrackerColors.green500,
            contentColor = HabitTrackerColors.backgroundColor
        ),
        modifier = Modifier.heightIn(48.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_plus_24dp),
            contentDescription = null,
            colorFilter = ColorFilter.tint(HabitTrackerColors.backgroundColor),
            modifier = Modifier.size(24.dp)
        )
    }
}