package com.example.posterlife.ui

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.posterlife.ui.loginUI.Login
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


sealed class Profil(val rute: String) {

    object ProfilUI : Profil("profilStart") {

        @Composable
        fun ProfilUI(navController: NavController) {
            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    ProfilTopBar()
                },
                content = {
                    ProfilContent(navController = navController)

                }

            )

        }
    }

    @Composable
    fun ProfilTopBar() {

        TopAppBar(
            title = {

                Text(
                    text = "Profil",
                    color = Color.Black,
                    fontSize = 30.sp
                )
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
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
                    .clickable(onClick = {navController.navigate("Login"){
                        popUpTo("Login.LoginScreen.LoginStart")

                    } }),
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
                    .clickable(onClick = {/* TODO */}),
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
                    .clickable(onClick = {navController.navigate("Mine Design"){
                        popUpTo("MineDesign.MineDesignStart.MineDesignStart")
                    } }),
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
                    .clickable(onClick = {/* TODO */}),
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(60.dp)
                    .clickable(onClick = {/* TODO */}),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Følg os",
                    fontWeight = FontWeight.Light,
                    fontSize = 22.sp
                )
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )

            }
            // række til at tilgå siden med kundeservice information
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable(onClick = {/* TODO */}),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Kundeservice",
                    fontWeight = FontWeight.Light,
                    fontSize = 22.sp
                )
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )

            }
            // række til at tilgå Betalingsinstillinger
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(60.dp)
                    .clickable(onClick = {/* TODO */}),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Betalingsinstillinger",
                    fontWeight = FontWeight.Light,
                    fontSize = 22.sp
                )
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )

            }

        }
    }


}
