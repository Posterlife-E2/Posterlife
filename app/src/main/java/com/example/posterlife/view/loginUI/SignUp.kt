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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posterlife.loginController.AuthenticationSignUp
import com.example.posterlife.R

/**
 * @Author Kristoffer Pedersen s205354
 *
 * @Source https://stackoverflow.com/questions/65304229/toggle-password-field-jetpack-compose
 */

sealed class SignUp(val route: String) {

    object SignUpScreen : SignUp("signUpScreen") {

        private var passwordForkert = mutableStateOf(false)

        @Composable
        fun SignUpScreen(navController: NavController) {

            Column(
                Modifier
                    .background(Color(0xfffcfcf0))
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .align(Alignment.CenterHorizontally)

                ) {

                    Image(
                        painter = painterResource(id = R.drawable.baggrundsbillede),
                        contentDescription = "background_SignUp",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        Alignment.TopCenter
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Opret Bruger",
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(25.dp)
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

                            colors = if (passwordForkert.value) {
                                TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.Red,
                                    focusedLabelColor = Color.Red,
                                    unfocusedBorderColor = Color.Red
                                )
                            } else {
                                TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.Black,
                                    focusedLabelColor = Color.Black
                                )
                            },

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

                            colors = if (passwordForkert.value) {
                                TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.Red,
                                    focusedLabelColor = Color.Red,
                                    unfocusedBorderColor = Color.Red
                                )
                            } else {
                                TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.Black,
                                    focusedLabelColor = Color.Black
                                )
                            },

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
                        passwordForkert.value = passwordValue != passwordConfirmValue



                        TextButton(
                            onClick = {
                                if (emailValue != "" && passwordValue != "") {
                                    AuthenticationSignUp.SignUpUser(
                                        emailValue,
                                        passwordValue,
                                        navController
                                    )
                                }
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
    }
}