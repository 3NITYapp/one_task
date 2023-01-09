package com.task.onetaskapp.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.task.onetaskapp.R


val sailec = FontFamily(
    Font(R.font.sailec_regular),
    Font(R.font.sailec_medium, FontWeight.SemiBold),
    Font(R.font.sailec_bold, FontWeight.Bold)
)

val promt = FontFamily(
    Font(R.font.prompt_small, FontWeight.W300),
    Font(R.font.prompt_regular, FontWeight.W400),
    Font(R.font.prompt_semibold, FontWeight.W600)

)

val merriweathersans = FontFamily(
    Font(R.font.merriweathersans_bold, FontWeight.W700),
    Font(R.font.merriweathersans_extrabold, FontWeight.W800),
)

data class AppTypography(

    val bigTitle: TextStyle = TextStyle(
        fontFamily = sailec,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
    ),

    val subtitle: TextStyle = TextStyle(
        fontFamily = sailec,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),

    val body: TextStyle = TextStyle(
        fontFamily = sailec,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp
    ),

    val button: TextStyle = TextStyle(
        fontFamily = sailec,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),

    val caption: TextStyle = TextStyle(
        fontFamily = sailec,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    val overline: TextStyle = TextStyle(
        fontFamily = sailec,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        letterSpacing = 1.25.sp
    ),


    val merriweathersans24spW800: TextStyle = TextStyle(
        fontFamily = merriweathersans,
        fontWeight = FontWeight.W800,
        fontSize = 24.sp
    ),

    val promt20spW600: TextStyle = TextStyle(
        fontFamily = promt,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),

    val promt48spW500: TextStyle = TextStyle(
        fontFamily = promt,
        fontWeight = FontWeight.W500,
        fontSize = 48.sp
    ),

    val promt14spW400: TextStyle = TextStyle(
        fontFamily = promt,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),

    val merriweathersans16spW700: TextStyle = TextStyle(
        fontFamily = merriweathersans,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp
    ),

    val merriweathersans20spW700: TextStyle = TextStyle(
        fontFamily = merriweathersans,
        fontWeight = FontWeight.W700,
        fontSize = 20.sp
    ),

    val promt24spW600: TextStyle = TextStyle(
        fontFamily = promt,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),

    val promt14spW600: TextStyle = TextStyle(
        fontFamily = promt,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp
    ),

    val merriweathersans16spW800: TextStyle = TextStyle(
        fontFamily = merriweathersans,
        fontWeight = FontWeight.W800,
        fontSize = 16.sp),

    val promt16spW600: TextStyle = TextStyle(
        fontFamily = promt,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),

    val promt16spW400: TextStyle = TextStyle(
        fontFamily = promt,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),

    val promt12spW400: TextStyle = TextStyle(
        fontFamily = promt,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    ),

    val promt14spW300: TextStyle = TextStyle(
        fontFamily = promt,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),

)

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }