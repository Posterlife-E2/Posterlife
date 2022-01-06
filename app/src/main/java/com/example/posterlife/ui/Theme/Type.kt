package com.example.posterlife.UI.Theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.posterlife.R

//Fra posterlife
private val Khand = FontFamily(
    Font(R.font.khand_regular),
    Font(R.font.khand_medium, FontWeight.Medium),
    Font(R.font.khand_light, FontWeight.Light),
    Font(R.font.khand_bold, FontWeight.Bold),
    Font(R.font.khand_semibold, FontWeight.SemiBold)
)

//skal redigeres/tilføjes flere fonts
val PosterlifeTypography = Typography(
    h4 = TextStyle(
        fontFamily = Khand,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = Khand,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = Khand,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Khand,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Khand,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Khand,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Khand,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = Khand,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = Khand,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),
    overline = TextStyle(
        fontFamily = Khand,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
)