package com.example.posterlife.UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.posterlife.UI.LoginUI.Login

sealed class Profil(val rute: String) {

    object ProfilUI: Profil("start") {

        @Composable
        fun ProfilUI(navController: NavController) {

            Button(onClick = {navController.navigate(Login.LoginPrompt.route)}) {
                Text("Login")
            }

            Text("Profil")

        }
    }

}
