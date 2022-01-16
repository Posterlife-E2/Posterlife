package com.example.posterlife.view.profilUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posterlife.R
import com.example.posterlife.loginController.AuthenticationLogin
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


sealed class Profil(val rute: String) {

    object ProfilUI : Profil("profilStart") {

        @Composable
        fun ProfilUI(navController: NavController) {
            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    ProfilTopBar(navController)
                },
                content = {
                    ProfilContent(navController = navController)

                }

            )

        }
    }

    @Composable
    fun ProfilTopBar(navController: NavController) {

        TopAppBar(
            title = {

                Text(
                    text = "Profil",
                    color = Color.Black,
                    fontSize = 30.sp
                )
            },
            actions = {
                IconButton(onClick = {
                    navController.navigate("Favorit") {
                        navController.popBackStack()
                    }
                }) {
                    Icon(
                        Icons.Filled.Favorite,
                        tint = Color.Red,
                        contentDescription = null
                    )
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.ShoppingCart, contentDescription = null)
                }
            },

            backgroundColor = Color(0xfffcfcf0),

            elevation = 12.dp
        )

    }

    @Composable
    fun ProfilContent(navController: NavController) {
        Column(
            Modifier
                .background(Color(0xfffcfcf0))
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            // række til at tilgå Login siden.
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .height(60.dp)
                        .padding(start = 8.dp)
                        .clickable(onClick = {
                            navController.navigate("Login") {
                                navController.popBackStack()

                            }
                        }),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Login",
                        fontWeight = FontWeight.Light,
                        fontSize = 22.sp
                    )
                    Icon(
                        imageVector = Icons.Filled.NavigateNext,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )


            }
            // række til at tilgå Deling med venner
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(8.dp)
                    .clickable(onClick = {
                        navController.navigate("delStart") {
                            navController.popBackStack()
                        }
                    }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Del med dine venner",
                    fontWeight = FontWeight.Light,
                    fontSize = 22.sp
                )
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )

            }
            // række til at tilgå Mine design.
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(60.dp)
                    .padding(start = 8.dp)
                    .clickable(onClick = {
                        navController.navigate("Mine Design") {
                            //sletter hele back stack før navigation
                            navController.popBackStack()
                        }
                    }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Mine Design",
                    fontWeight = FontWeight.Light,
                    fontSize = 22.sp
                )
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )

            }
            // række til at tilgå siden med favoritter
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 8.dp)
                    .clickable(onClick = {
                        navController.navigate("favorit") {
                            navController.popBackStack()
                        }
                    }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Favoritter",
                    fontWeight = FontWeight.Light,
                    fontSize = 22.sp
                )
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )

            }
            // række til at tilgå side hvor man kan læse om privatelivspolitik.
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(60.dp)
                    .padding(start = 8.dp)
                    .clickable(onClick = {navController.navigate("privatPolitik"){
                        navController.popBackStack()
                    } }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Privatlivspolitik",
                    fontWeight = FontWeight.Light,
                    fontSize = 22.sp
                )
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )

            }
            // række til at tilgå Handelsbetingelser
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 8.dp)
                    .clickable(onClick = {
                        navController.navigate("betingelser") {
                            navController.popBackStack()
                        }
                    }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Handelsbetingelser",
                    fontWeight = FontWeight.Light,
                    fontSize = 22.sp
                )
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )

            }
            // række til at tilgå siden med kontaktinformation Posterlife
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(60.dp)
                    .padding(start = 8.dp)
                    .clickable(onClick = {
                        navController.navigate("kontakt") {
                            navController.popBackStack()
                        }
                    }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Kontakt os",
                    fontWeight = FontWeight.Light,
                    fontSize = 22.sp
                )
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )


            }
            Spacer(modifier = Modifier.padding(110.dp))

            //row med ikoner hvor man bliver linket til posterlifes sider på de sociale medier.
            Row(modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom)
            {
                    Image(
                        painter = painterResource(id = R.drawable.facebookiconposterlife),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { /*TODO*/}

                        )
                    Image(
                        painter = painterResource(id = R.drawable.instaiconposterlife),
                        contentDescription = null,
                        modifier = Modifier
                            .size(22.dp)
                            .clickable { /*TODO*/ }

                        )

                    Image(
                        painter = painterResource(id = R.drawable.linkedinicon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(22.dp)
                            .clickable {/*TODO*/}

                        )
            }

        }

    }
}

