package com.example.posterlife.UI.LoginUI

import androidx.compose.runtime.Composable


sealed class Login(val rute: String)  {

    object LoginPrompt : Login("login") {

        @Composable
        fun LoginStart() {

        }



    }
}