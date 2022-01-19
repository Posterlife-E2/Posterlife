package com.example.posterlife.view.profilUI

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
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
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posterlife.R
import com.example.posterlife.view.NavigationBundNav

/**
 * @Author Camilla Bøjden, (s205360)
 */

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

    // TopAppBar til Profilsiden.
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
            // indkøbskurv magler stadig funktionalitet.
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.ShoppingCart, contentDescription = null)
                }
            },

            backgroundColor = Color(0xfffcfcf0),

            elevation = 12.dp
        )

    }

    // Denne funktion indeholder profilsidens indhold. Denne består af flere rows der hver har en tekst og et icon som indikere af brugeren kan trykke videre.
    @Composable
    fun ProfilContent(navController: NavController) {
        val context = LocalContext.current

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
                            popUpTo(NavigationBundNav.Profil.route)

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
                            popUpTo(NavigationBundNav.Profil.route)
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
                            popUpTo(NavigationBundNav.Profil.route)
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
            // række til at tilgå sin ordrehistorik. mangler funktionalitet.
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 8.dp)
                    .clickable(onClick = {/*TODO*/ }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Ordrehistorik",
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
                    .clickable(onClick = {
                        navController.navigate("privatPolitik") {
                            popUpTo(NavigationBundNav.Profil.route)
                        }
                    }),
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
                            popUpTo(NavigationBundNav.Profil.route)
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
                            popUpTo(NavigationBundNav.Profil.route)
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
            /**
             *  @Source https://stackoverflow.com/questions/66801838/how-do-i-programmatically-open-an-external-url-on-button-click-with-jetpack-comp?fbclid=IwAR1n9oUU0LJ3xaFE633WAfAGWPlO6Q9cwIZoJ2AUAmNU5yHaXhRM6ifEAJo
             *  Række der indeholder billeder som er clickable der gør det muligt for brugeren at komme ind på Posterlifes egne sider.
             */
            Spacer(modifier = Modifier.padding(100.dp))

            //row med ikoner hvor man bliver linket til posterlifes sider på de sociale medier.
            Row(
                modifier = Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            )
            {


                Image(
                    painter = painterResource(id = R.drawable.facebookiconposterlife),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(onClick = {
                            CustomTabsIntent
                                .Builder()
                                .build()
                                .launchUrl(
                                    context,
                                    Uri.parse("https://www.facebook.com/Posterlife.dk")
                                )
                        }
                        )

                )
                Image(
                    painter = painterResource(id = R.drawable.instaiconposterlife),
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp)
                        .clickable(onClick = {
                            CustomTabsIntent
                                .Builder()
                                .build()
                                .launchUrl(
                                    context,
                                    Uri.parse("https://www.instagram.com/posterlife.dk/")
                                )
                        }
                        )

                )

                Image(
                    painter = painterResource(id = R.drawable.linkedinicon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp)
                        .clickable(onClick = {
                            CustomTabsIntent
                                .Builder()
                                .build()
                                .launchUrl(
                                    context,
                                    Uri.parse("https://www.linkedin.com/company/posterlife/")
                                )
                        }
                        )
                )
            }

        }

    }

}



