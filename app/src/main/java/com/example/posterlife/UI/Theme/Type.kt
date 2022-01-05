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
    body1 = TextStyle(
        fontFamily = Khand,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

)