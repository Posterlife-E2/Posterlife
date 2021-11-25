package com.example.posterlife.UI

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

sealed class Hjem (route: String){

    object HjemStart : Hjem("hjemstart") {

        @Composable
        fun HjemStartUI(navController: NavController) {
            Text(";This is hjem")

        }
    }
}