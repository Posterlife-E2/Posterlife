package com.example.posterlife.UI

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.posterlife.UI.LoginUI.Login
import com.google.firebase.auth.FirebaseUser

sealed class Profil(val rute: String) {

    object ProfilUI: Profil("start") {

        @Composable
        fun ProfilUI(navController: NavController) {

            Button(onClick = {navController.navigate(Login.LoginScreen.route)}) {
                Text("Login")
            }

            Text("Profil")

        }
    }

}
