package com.example.posterlife.ui

import com.example.posterlife.R
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

sealed class Handelsbetingelser(val route: String) {
    object Betingelser : Handelsbetingelser("betingelser")


    @ExperimentalFoundationApi
    @Composable
    fun BetingelserOverview(navController: NavController) {


        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                BetingelserTopBar()

            },
            content = {
                BetingelserPage(navController = navController)
            }
        )

    }

    @Composable
    fun BetingelserTopBar() {
        TopAppBar(
            title = {

                Text(
                    text = "Handelsbetingelser",
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

    @ExperimentalFoundationApi
    @Composable
    fun BetingelserPage(navController: NavController) {

        Column(
            Modifier
                .background(Color(0xfffcfcf0))
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            // række til at tilgå Leveringsoplysninger
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(60.dp)
                    .clickable(onClick = {

                    }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Levering",
                    fontWeight = FontWeight.Light,
                    fontSize = 22.sp
                )
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )


            }
            // række til at tilgå Betaling
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable(onClick = { }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Betaling",
                    fontWeight = FontWeight.Light,
                    fontSize = 22.sp
                )
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )

            }
            // række til at tilgå Reklamation
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(60.dp)
                    .clickable(onClick = {
                    }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Reklamation",
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


            @Composable
            fun Levering() {
                val context = LocalContext.current
                Column(
                    modifier = Modifier
                        .background(Color(0xfffcfcf0))
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {

                    Text(
                        text = "Levering",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = context.getString(R.string.Levering),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }
            }
        }

    }

