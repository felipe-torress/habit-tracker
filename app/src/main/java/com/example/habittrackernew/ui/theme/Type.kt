package com.example.habittrackernew.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.habittrackernew.R

//region --- Font Families ---
val firaFamily = FontFamily(
    Font(resId = R.font.fira_mono_bold, weight = FontWeight.Bold),
    Font(resId = R.font.fira_mono_medium, weight = FontWeight.Medium),
    Font(resId = R.font.fira_mono_regular, weight = FontWeight.Normal),
)

val satoshiFamily = FontFamily(
    Font(resId = R.font.satoshi_black, weight = FontWeight.Black),
    Font(resId = R.font.satoshi_black_italic, weight = FontWeight.Black, style = FontStyle.Italic),
    Font(resId = R.font.satoshi_bold, weight = FontWeight.Bold),
    Font(resId = R.font.satoshi_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resId = R.font.satoshi_medium, weight = FontWeight.Medium),
    Font(resId = R.font.satoshi_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(resId = R.font.satoshi_regular, weight = FontWeight.Normal),
    Font(resId = R.font.satoshi_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(resId = R.font.satoshi_light, weight = FontWeight.Light),
    Font(resId = R.font.satoshi_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
)
//endregion

object HabitTrackerTypography {
    // Satoshi
    val headline4 = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Black,
        fontSize = 32.sp
    )

    val subtitle1 = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )

    val bodyLarge = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )

    val bodySmall = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )

    val caption = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )

    // Fira Mono
    val bodySmallFira = TextStyle(
        fontFamily = firaFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
}