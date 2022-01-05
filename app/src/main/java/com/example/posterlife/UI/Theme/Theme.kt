package com.example.posterlife.UI.Theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * https://developer.android.com/jetpack/compose/themes/material
 */

@SuppressLint("ConflictingOnColor")
private val LightTheme = lightColors(
    primary = KnækketHvid,
    primaryVariant = Hvid,
    onPrimary = Sort,
    secondary = Grå,
    secondaryVariant = Mørkegrå,
    onSecondary = Hvid,
    error = Hvid
)
private val DarkTheme = darkColors(
)

@Composable
fun PosterlifeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkTheme else LightTheme,
        typography = PosterlifeTypography,
        shapes = PosterlifeShapes,
        content = content
    )
}

@Preview("testItem")
@Composable
private fun testItem() {
    PosterlifeTheme {
        Column {
            Text(text = "testmig")
        }
    }
}
