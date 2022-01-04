package com.example.posterlife.ui

import androidx.compose.foundation.background
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.posterlife.ui.loginUI.Login
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*


sealed class Profil(val rute: String) {

    object ProfilUI : Profil("profilStart") {

        @Composable
        fun ProfilUI(navController: NavController) {

            Column(
                Modifier
                    .background(Color(0xfffcfcf0))
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {

                Button(onClick = { navController.navigate(Login.LoginScreen.route) }) {
                    Text("Login")
                }
            }


        }
    }

}
