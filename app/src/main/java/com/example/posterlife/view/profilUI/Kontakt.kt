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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posterlife.R
import com.example.posterlife.view.NavigationBundNav

/**
 * @Author Camilla Bøjden (s205360)
 */

sealed class Kontakt(val route : String) {
    object KontaktInfo : Kontakt("kontakt")

    @Composable
    fun KontaktOverview(navController: NavController){

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                KontaktTopBar(navController = navController)

            },
            content = {
                KontaktPage()

            }
        )

    }

    // Top app bar til kontaktsiden. Den indeholder et ikon så det er muligt at gå tilbage.
    @Composable
    fun KontaktTopBar(navController: NavController) {
        TopAppBar(
            title = {

                Text(
                    text = "Kontakt os",
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

    // Denne funktion indeholder kontaktinformation til Posterlife. Dens tekst er en string der er defineret i res mappen.
    @Composable
    fun KontaktPage() {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .background(Color(0xfffcfcf0))
                .fillMaxWidth()
                .fillMaxHeight()

        ) {

            Text(text = context.getString(R.string.KontaktOs),
                fontStyle = FontStyle.Italic,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(top = 25.dp, start = 10.dp, end = 10.dp))

            Text(text = "Posterlife:",
            fontStyle = FontStyle.Italic,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 25.dp, start = 10.dp, end = 10.dp)
            )


            Text(text = context.getString(R.string.MailInfo),
                fontStyle = FontStyle.Italic,
                fontSize = 17.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp))

            Text(text = "Addresse:",
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 25.dp, start = 10.dp, end = 10.dp)
            )


            Text(text = context.getString(R.string.Addresse),
                fontStyle = FontStyle.Italic,
                fontSize = 17.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp))

            Text(text = "CVR:",
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 25.dp, start = 10.dp, end = 10.dp)
            )

            Text(text = context.getString(R.string.CVR),
                fontStyle = FontStyle.Italic,
                fontSize = 17.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp))

        }
    }



}