package com.example.posterlife.view.profilUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
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

sealed class Levering(val route: String) {
    object LeveringUI : Levering("levering")

    @Composable
    fun LeveringOverview(navController: NavController) {


        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                LeveringTopBar(navController = navController)

            },
            content = {
                LeveringPage()

            }
        )
    }

    // top app bar til leveringssiden. den indeholder et ikon så det er muligt at gå tilbage.
    @Composable
    fun LeveringTopBar(navController: NavController) {
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
    fun LeveringPage() {
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
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 5.dp)
            )

            Text(text = context.getString(R.string.LeveringInfo),
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 25.dp, start = 10.dp, end = 10.dp))

            Text(
                text = context.getString(R.string.Levering),
                fontStyle = FontStyle.Italic,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 25.dp, start = 10.dp, end = 10.dp)
            )
        }
    }

}
