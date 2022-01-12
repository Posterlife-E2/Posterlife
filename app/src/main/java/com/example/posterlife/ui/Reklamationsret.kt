package com.example.posterlife.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

sealed class Reklamationsret(val route: String) {
    object Reklamation : Reklamationsret("reklamation")

    @Composable
    fun ReklamationUI(navController: NavController) {


        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                ReklamationTopBar(navController = navController)

            },
            content = {


            }
        )
    }


    // top app bar til Betalingssiden. Den indeholder et ikon så det er muligt at gå tilbage til handelsbetingelser.
    @Composable
    fun ReklamationTopBar(navController: NavController) {
        TopAppBar(
            title = {

                Text(
                    text = "Reklamation",
                    color = Color.Black,
                    fontSize = 30.sp
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate("betingelser") {
                        popUpTo("BetingelserOverview")
                    }
                }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        tint = Color.Black,
                        contentDescription = null
                    )
                }
            },
            backgroundColor = Color(0xfffcfcf0),

            elevation = 12.dp
        )

    }







}
