package com.example.posterlife.view.loginUI

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posterlife.loginController.AuthenticationLogin
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.posterlife.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

/**
 * @Author Kristoffer Pedersen s205354, Camilla Bøjden (s205360)
 *
 * @Source https://foso.github.io/Jetpack-Compose-Playground/material/textfield/
 * @Source https://stackoverflow.com/questions/65304229/toggle-password-field-jetpack-compose
 * @Source https://www.pngkit.com/view/u2w7q8o0r5e6w7w7_login-with-facebook-button-png-facebook-login-button/?fbclid=IwAR0djcebIY0rdN7v-8neduRGFUmgeTtyKfPW_nyt6w3g0fJF9SaB7IXJQR4
 */

open class Login(val route: String) {

    object LoginScreen : Login("login") {


        @Composable
        fun LoginStart(navController: NavController) {

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

                ) {
                    // baggrundsbillede
                    Image(
                        painter = painterResource(id = R.drawable.baggrundsbillede),
                        contentDescription = "background_image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        Alignment.TopCenter
                    )

                    Column(modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),verticalArrangement = Arrangement.Center) {

                    //Logo
                    val logo: Painter = painterResource(id = R.drawable.posterlife_logo)
                    Image(
                        painter = logo, contentDescription = "",
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

                        Row() {

                            // login
                            TextButton(
                                onClick = {

                                    if (emailValue != "" && passwordValue != ""){
                                        AuthenticationLogin.signIn(
                                            emailValue,
                                            passwordValue,
                                            navController
                                        )
                                    }
                                },
                                modifier = Modifier
                                    .padding(16.dp)
                                    .height(50.dp)
                                    .width(180.dp),

                                colors = ButtonDefaults.textButtonColors(
                                    backgroundColor = Color.Black, contentColor = Color.White
                                )

                            ) {
                                Text(text = "Log ind")
                            }

                            //opret bruger
                            OutlinedButton(
                                onClick = { navController.navigate("signUpScreen") },
                                modifier = Modifier
                                    .padding(16.dp)
                                    .height(50.dp)
                                    .width(180.dp),

                                colors = ButtonDefaults.textButtonColors(
                                    backgroundColor = Color.White, contentColor = Color.Black
                                )

                            ) {
                                Text(text = "Opret Bruger")
                            }
                        }

                        Text(text = "Eller login med",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 20.dp, bottom = 30.dp)
                        )
                        // facebook billede der er gjordt clickable.
                            Image(
                                painter = painterResource(id = R.drawable.facebookbutton),
                                contentDescription = "facebookbutton",
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(180.dp)
                                    .clickable() {  }
                                    .align(Alignment.CenterHorizontally)
                            )

                    }

                }
            }
        }


    }

}