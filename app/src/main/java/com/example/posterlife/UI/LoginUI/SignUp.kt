package com.example.posterlife.UI.LoginUI

import androidx.compose.runtime.Composable

sealed class SignUp(val route: String) {

    object SignUpScreen : SignUp("signUpScreen") {

        @Composable
        fun SignUpScreen(){

        }
    }
}

