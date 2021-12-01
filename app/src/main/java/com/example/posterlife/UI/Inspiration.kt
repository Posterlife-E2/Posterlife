package com.example.posterlife.UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.ui.Modifier

/**
 * @source https://developer.android.com/jetpack/compose/navigation
 */

sealed class Inspiration(val rute: String) {

    object InspirationStart : Inspiration("start") {

        @Composable
        fun InspirationMainUI(navController: NavController) {

            Column(Modifier
                .background(Color(0xfffcfcf0))
                .fillMaxHeight()
                .fillMaxWidth()
            )
            {

            }
        }
    }

    object InspirationOther : Inspiration("other") {

        @Composable
        fun InspirationOtherUI(navController: NavController) {
            Text("Get Scuffed")
        }
    }
}