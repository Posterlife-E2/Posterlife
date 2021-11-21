package com.example.posterlife.UI.LoginUI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posterlife.UI.Profil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

sealed class SignUp(val route: String) {

    object SignUpScreen : SignUp("signUpScreen") {

        @Composable
        fun SignUpScreen(navController: NavController) {

            authentication = Firebase.auth

            val user = authentication.currentUser
            if (user != null) {
            }

            Column(Modifier.padding(16.dp)) {

                //Email
                var emailValue by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = emailValue,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),

                    label = { Text("E-mail adresse") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        focusedLabelColor = Color(0xfffc9003)
                    ),

                    //Tekst inden i tekstboksen
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                    ),
                    // Når man skriver, så ændrer teksten sig.
                    onValueChange = {
                        emailValue = it
                    }
                )

                //Password
                var passwordValue by remember { mutableStateOf("") }
                var passwordVisibility by remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = passwordValue,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),

                    // Når man skriver, så ændrer teksten sig.
                    onValueChange = { passwordValue = it },

                    label = { Text("Adgangskode") },

                    visualTransformation =

                    if (passwordVisibility) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        focusedLabelColor = Color(0xfffc9003)
                    ),

                    //Tekst inden i tekstboksen
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                    ),

                    trailingIcon = {
                        val visibilityIcon = if (passwordVisibility) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        }


                        IconButton(onClick = {
                            passwordVisibility = !passwordVisibility
                        }) {
                            Icon(imageVector = visibilityIcon, "")
                        }
                    }
                )
                TextButton(
                    onClick = {
                        SignUpUser(
                            emailValue,
                            passwordValue,
                            navController
                        )
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),

                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.Black, contentColor = Color.White
                    )

                ) {
                    Text(text = "Opret Bruger")
                }
            }


        }

        private lateinit var authentication: FirebaseAuth

        fun SignUpUser(email: String, password: String, navController: NavController) {

            authentication.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        authentication.currentUser
                        navController.navigate(Profil.ProfilUI.rute)
                        //UpdateUI something here.
                    } else {
                    }
                    //Update UI something here

                }
        }
    }
}