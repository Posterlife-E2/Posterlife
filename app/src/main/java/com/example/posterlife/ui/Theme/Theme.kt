package com.example.posterlife.UI.Theme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


/**
 * https://developer.android.com/jetpack/compose/themes/material
 */

@SuppressLint("ConflictingOnColor")
private val LightTheme = lightColors(
    primary = KnaekketHvid,
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
        //shapes = PosterlifeShapes,
        content = content
    )
}

@Preview("testItem1")
@Composable
private fun testItem() {
    PosterlifeTheme {
        Column(
            Modifier
                .background(MaterialTheme.colors.primary)
        ) {
            Text(text = "testmig æøå 123",
                 style = MaterialTheme.typography.button)
        }
    }
}

@Preview("testItem2")
@Composable
private fun testItemTwo() {
    PosterlifeTheme {
        Column(Modifier
            .background(MaterialTheme.colors.secondary)) {
            Text(text = "testmigigen æøå 123",
                 style = MaterialTheme.typography.h4)

        }
    }
}