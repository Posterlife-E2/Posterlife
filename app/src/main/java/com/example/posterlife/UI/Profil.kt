package com.example.posterlife.UI

import androidx.compose.foundation.background
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.posterlife.UI.LoginUI.Login
import com.google.firebase.auth.FirebaseUser
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*


sealed class Profil(val rute: String) {

    object ProfilUI : Profil("start") {

        @Composable
        fun ProfilUI(navController: NavController) {

            Column(Modifier
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
