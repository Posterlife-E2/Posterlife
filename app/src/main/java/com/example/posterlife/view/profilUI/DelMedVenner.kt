package com.example.posterlife.view.profilUI

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posterlife.R
import com.example.posterlife.view.NavigationBundNav

/**
 * @Author Camilla Bøjden, (s205360)
 */

sealed class DelMedVenner(val route: String) {

    object DelStart : DelMedVenner("delStart")

    @ExperimentalFoundationApi
    @Composable
    fun DelOverview(navController: NavController) {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                DelTopBar(navController = navController)

            },
            content = {
                DelPage()
            }
        )
    }

    // TopAppBar til delsiden. Den indeholder et ikon så det er muligt at gå tilbage til profilsiden.
    @Composable
    fun DelTopBar(navController: NavController) {
        TopAppBar(
            title = {

                Text(
                    text = "Del med venner",
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

    /**
     * Del sidens indhold. Denne består af rows, som hver har et billede der er gjordt clickable,
     * som på sigt skal give brugeren mulighed for at dele sit billede på sociale medier, dorfor står de stadig som TODO.
     */


    @ExperimentalFoundationApi
    @Composable
    fun DelPage() {
        Column(
            modifier = Modifier
                .background(Color(0xfffcfcf0))
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Del dine plakater med dine venner!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 15.dp)
            )

            Spacer(modifier = Modifier.height(22.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /*TODO*/ },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Image(
                    painterResource(R.drawable.facebook),
                    contentDescription = "Facebook",
                    modifier = Modifier
                        .size(110.dp)
                        .padding(start = 35.dp)
                )
                Text(
                    text = "Del plakat på Facebook",
                    fontSize = 20.sp,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /*TODO*/ },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Image(
                    painterResource(R.drawable.instagram),
                    contentDescription = "Instagram",
                    modifier = Modifier
                        .size(110.dp)
                        .padding(start = 35.dp),
                )
                Text(
                    text = "Del plakat på Instagram",
                    fontSize = 20.sp,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {  /*TODO*/ },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Image(
                    painterResource(R.drawable.pinterest),
                    contentDescription = "Pinterest",
                    modifier = Modifier
                        .size(110.dp)
                        .padding(start = 35.dp),
                )
                Text(
                    text = "Del plakat på Pinterest",
                    fontSize = 20.sp,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {  /*TODO*/ },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Image(
                    painterResource(R.drawable.twitter),
                    contentDescription = "Twitter",
                    modifier = Modifier
                        .size(110.dp)
                        .padding(start = 35.dp),
                )
                Text(
                    text = "Del plakat på Twitter",
                    fontSize = 20.sp,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {  /*TODO*/ },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Image(
                    painterResource(R.drawable.mail),
                    contentDescription = "Mail",
                    modifier = Modifier
                        .size(110.dp)
                        .padding(start = 35.dp),
                )
                Text(
                    text = "Del plakat på Mail",
                    fontSize = 20.sp,
                )
            }


        }

    }

}
