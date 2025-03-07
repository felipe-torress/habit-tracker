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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.habittrackernew.R
import com.example.habittrackernew.ui.screens.habits.model.ColorUI
import com.example.habittrackernew.ui.theme.HabitTrackerColors
import com.example.habittrackernew.ui.theme.HabitTrackerTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    hasNavigationIcon: Boolean = false,
    hasActionButton: Boolean = false,
    navigationIcon: @Composable (onClick: () -> Unit, color: Color) -> Unit = { onClick, color ->
        CollapseIcon(
            onClick = onClick,
            color = color,
        )
    },
    actionButton: @Composable RowScope.(onClick: () -> Unit) -> Unit = { onClick -> AddButton(onClick) },
    onNavigationIconClick: () -> Unit = {},
    onActionButtonClick: () -> Unit = {},
    navigationIconColor: Color = HabitTrackerColors.blue800,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Title(title) },
        navigationIcon = { if (hasNavigationIcon) navigationIcon(onNavigationIconClick, navigationIconColor) },
        actions = { if (hasActionButton) actionButton(onActionButtonClick) },
        scrollBehavior = pinnedScrollBehavior(),
        colors =
            TopAppBarColors(
                containerColor = HabitTrackerColors.backgroundColor,
                scrolledContainerColor = HabitTrackerColors.backgroundColor,
                navigationIconContentColor = HabitTrackerColors.green500,
                titleContentColor = HabitTrackerColors.textColor,
                actionIconContentColor = HabitTrackerColors.green500,
            ),
        modifier = modifier.background(HabitTrackerColors.backgroundColor),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradientTopBar(
    title: String,
    hasNavigationIcon: Boolean = false,
    hasActionButton: Boolean = false,
    navigationIcon: @Composable (onClick: () -> Unit, color: Color) -> Unit = { onClick, color ->
        CollapseIcon(
            onClick = onClick,
            color = color,
        )
    },
    actionButton: @Composable RowScope.(onClick: () -> Unit) -> Unit = { onClick -> AddButton(onClick) },
    onNavigationIconClick: () -> Unit = {},
    onActionButtonClick: () -> Unit = {},
    color: ColorUI,
    modifier: Modifier = Modifier,
) {
    val navigationIconColor = when (color) {
        ColorUI.BLUE -> HabitTrackerColors.blue800
        ColorUI.GREEN -> HabitTrackerColors.green800
        ColorUI.PURPLE -> HabitTrackerColors.purple800
    }

    val (backgroundTopColor, backgroundBottomColor) = remember {
        when (color) {
            ColorUI.BLUE -> HabitTrackerColors.blue200 to HabitTrackerColors.softBlue
            ColorUI.GREEN -> HabitTrackerColors.green200 to HabitTrackerColors.softGreen
            ColorUI.PURPLE -> HabitTrackerColors.purple100 to HabitTrackerColors.softPurple
        }
    }

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(backgroundTopColor, backgroundBottomColor),
    )

    TopAppBar(
        title = { Title(title) },
        navigationIcon = { if (hasNavigationIcon) navigationIcon(onNavigationIconClick, navigationIconColor) },
        actions = { if (hasActionButton) actionButton(onActionButtonClick) },
        scrollBehavior = pinnedScrollBehavior(),
        colors =
            TopAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = HabitTrackerColors.backgroundColor,
                navigationIconContentColor = HabitTrackerColors.green500,
                titleContentColor = HabitTrackerColors.textColor,
                actionIconContentColor = HabitTrackerColors.green500,
            ),
        modifier = modifier.background(backgroundGradient),
    )
}

//region --- Inner Components ---
@Composable
private fun Title(title: String) {
    Text(
        text = title,
        style = HabitTrackerTypography.headline4,
        color = HabitTrackerColors.textColor,
    )
}

@Composable
private fun CollapseIcon(
    onClick: () -> Unit,
    color: Color = HabitTrackerColors.blue800,
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(R.drawable.ic_minimize_24dp),
            contentDescription = "Collapse Icon",
            tint = color,
            modifier = Modifier.size(24.dp),
        )
    }
}

@Composable
private fun AddButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors =
            buttonColors(
                containerColor = HabitTrackerColors.green500,
                contentColor = HabitTrackerColors.backgroundColor,
            ),
        modifier = Modifier.heightIn(48.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_plus_24dp),
            contentDescription = null,
            colorFilter = ColorFilter.tint(HabitTrackerColors.backgroundColor),
            modifier = Modifier.size(24.dp),
        )
    }
}
//endregion

//region --- Preview - TopBar ---
private data class TopBarPreviewData(
    val title: String = "Title",
    val hasNavigationIcon: Boolean = false,
    val hasActionButton: Boolean = false,
)

private class TopBarPreviewParameterProvider : PreviewParameterProvider<TopBarPreviewData> {
    override val values = sequenceOf(
        // No Navigation Icon, No Action Button
        TopBarPreviewData(),
        // Navigation Icon, No Action Button
        TopBarPreviewData(hasNavigationIcon = true),
        // Action Button, No Navigation Icon
        TopBarPreviewData(hasActionButton = true),
        // Navigation Icon, Action Button
        TopBarPreviewData(hasNavigationIcon = true, hasActionButton = true),
    )
}

@Preview
@Composable
private fun TopBarPreview(
    @PreviewParameter(TopBarPreviewParameterProvider::class) previewData: TopBarPreviewData,
) {
    TopBar(
        title = previewData.title,
        hasNavigationIcon = previewData.hasNavigationIcon,
        hasActionButton = previewData.hasActionButton,
    )
}
//endregion

//region --- Preview - GradientTopBar ---
private data class GradientTopBarPreviewData(
    val title: String = "Title",
    val hasNavigationIcon: Boolean = false,
    val hasActionButton: Boolean = false,
    val color: ColorUI = ColorUI.BLUE,
)

private class GradientTopBarPreviewParameterProvider : PreviewParameterProvider<GradientTopBarPreviewData> {
    override val values = sequenceOf(
        // Blue - No Navigation Icon, No Action Button
        GradientTopBarPreviewData(),
        // Green
        GradientTopBarPreviewData(hasNavigationIcon = true, color = ColorUI.GREEN),
        // Purple
        GradientTopBarPreviewData(hasNavigationIcon = true, color = ColorUI.PURPLE),
        // Blue - Navigation Icon, No Action Button
        GradientTopBarPreviewData(hasNavigationIcon = true),
        // Blue - Action Button, No Navigation Icon
        GradientTopBarPreviewData(hasActionButton = true),
        // Blue - Navigation Icon, Action Button
        GradientTopBarPreviewData(hasNavigationIcon = true, hasActionButton = true),
    )
}

@Preview
@Composable
private fun GradientTopBarPreview(
    @PreviewParameter(GradientTopBarPreviewParameterProvider::class) previewData: GradientTopBarPreviewData,
) {
    GradientTopBar(
        title = previewData.title,
        hasNavigationIcon = previewData.hasNavigationIcon,
        hasActionButton = previewData.hasActionButton,
        color = previewData.color,
    )
}
//endregion
