package com.example.posterlife.UI

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

sealed class Inspiration(val rute: String) {

    object InspirationStart : Inspiration("start") {

        @Composable
        fun InspirationMainUI(navController: NavController) {
            Button(onClick = {navController.navigate(InspirationOther.rute)}) {
                
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