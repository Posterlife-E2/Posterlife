package com.example.posterlife.UI.LoginUI

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posterlife.Login.LoginCred

sealed class Login(val route: String) {

    object LoginPrompt : Login("login") {

        //val login = LoginCred
        /**
         * @Source https://foso.github.io/Jetpack-Compose-Playground/material/textfield/
         * @Source https://stackoverflow.com/questions/65304229/toggle-password-field-jetpack-compose
         */
        @Composable
        fun LoginStart() {

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

                    if(passwordVisibility) {
                        VisualTransformation.None
                    }
                    else {
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
                /**Button (onClick = {login.loginCred(emailValue,passwordValue)}) {

                }**/
            }

        }


    }

}

//Så man kan preview det man laver.
@Preview
    (
    showBackground = true
)
@Composable
fun PreviewLogin() {
    Login.LoginPrompt.LoginStart()
}