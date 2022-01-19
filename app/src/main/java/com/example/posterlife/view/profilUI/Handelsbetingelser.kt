package com.example.posterlife.view.profilUI

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posterlife.view.NavigationBundNav

/**
 * @Author Camilla Bøjden, (s205360)
 *
 * @Source https://posterlife.dk/handelsbetingelser/?fbclid=IwAR0djcebIY0rdN7v-8neduRGFUmgeTtyKfPW_nyt6w3g0fJF9SaB7IXJQR4
 */

sealed class Handelsbetingelser(val route: String) {
    object Betingelser : Handelsbetingelser("betingelser")


    @ExperimentalFoundationApi
    @Composable
    fun BetingelserOverview(navController: NavController) {


        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                BetingelserTopBar(navController = navController)

            },
            content = {
                BetingelserPage(navController = navController)
            }
        )

    }

    //top app bar til handelsbetingelser. Inderholder en tilbageknap så det er muligt at komme tilbage til profilsiden.
    @Composable
    fun BetingelserTopBar(navController: NavController) {
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
                    navController.navigate(NavigationBundNav.Profil.route) {
                        popUpTo("ProfilUI")
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

    //funktion der håndtere de forskellige vælg man har i handelsbetingelser. Består af tre rows.
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
                    .padding(start = 8.dp)
                    .clickable(onClick = {
                        navController.navigate("levering") {
                            popUpTo("LeveringOverview")
                        }

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
            // række til at tilgå Betalingsoplysninger.
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 8.dp)
                    .clickable(onClick = {
                        navController.navigate("betaling") {
                            popUpTo("BetalingOverview")
                        }
                    }),
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
                    .padding(start = 8.dp)
                    .clickable(onClick = {
                        navController.navigate("reklamation") {
                            popUpTo("ReklamationUI")
                        }
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

    }

}

