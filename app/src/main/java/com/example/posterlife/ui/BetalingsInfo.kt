package com.example.posterlife.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posterlife.R

sealed class BetalingsInfo(val route: String) {
    object InfoBetaling : BetalingsInfo("betaling")

    @Composable
    fun BetalingOverview(navController: NavController){
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                BetalingTopBar(navController = navController)

            },
            content = {
                BetalingsPage()


            }
        )
    }

    // top app bar til Betalingssiden. Den indeholder et ikon så det er muligt at gå tilbage til handelsbetingelser.
    @Composable
    fun BetalingTopBar(navController: NavController) {
        TopAppBar(
            title = {

                Text(
                    text = "Handelsbetingelser",
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

    // funktion der indeholder tekst til levering, denne tekst er defineret som en string i res mappen.
    @Composable
    fun BetalingsPage() {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .background(Color(0xfffcfcf0))
                .fillMaxWidth()
                .fillMaxHeight()

        ) {

            Text(
                text = "Betaling",
                fontSize = 30.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp)
            )

            Text(text = context.getString(R.string.BetalingInfo),
                fontStyle = FontStyle.Italic,
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(top = 25.dp, start = 10.dp, end = 10.dp).align(CenterHorizontally))


            Text(
                text = context.getString(R.string.Betaling),
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 25.dp, start = 10.dp, end = 10.dp)
            )
        }
    }




    }
