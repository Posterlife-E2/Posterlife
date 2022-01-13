package com.example.posterlife.view.loginUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posterlife.loginController.AuthenticationSignUp
import com.example.posterlife.R


sealed class SignUp(val route: String) {

    object SignUpScreen : SignUp("signUpScreen") {

        @Composable
        fun SignUpScreen(navController: NavController) {

            Column(
                Modifier
                    .background(Color(0xfffcfcf0))
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {

                //Logo
                val Logo: Painter = painterResource(id = R.drawable.posterlife_logo)
                Image(
                    painter = Logo, contentDescription = "",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(150.dp)
                )

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
                //Password
                //TODO check at passwordconfirm = password før at den kan sign up.
                var passwordConfirmValue by remember { mutableStateOf("") }
                var passwordConfirmVisibility by remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = passwordConfirmValue,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),

                    // Når man skriver, så ændrer teksten sig.
                    onValueChange = { passwordConfirmValue = it },

                    label = { Text("Bekræft Adgangskode") },

                    visualTransformation =

                    if (passwordConfirmVisibility) {
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
                        val visibilityIcon = if (passwordConfirmVisibility) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        }


                        IconButton(onClick = {
                            passwordConfirmVisibility = !passwordConfirmVisibility
                        }) {
                            Icon(imageVector = visibilityIcon, "")
                        }
                    }
                )
                TextButton(
                    onClick = {
                        AuthenticationSignUp.SignUpUser(
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
    }
}