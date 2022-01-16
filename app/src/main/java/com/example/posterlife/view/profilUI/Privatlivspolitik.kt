package com.example.posterlife.view.profilUI

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.ui.core.Text
import com.example.posterlife.R
import androidx.compose.material.Text as MaterialText

sealed class Privatlivspolitik(var route: String) {
    object privatpolitik : Privatlivspolitik("privatPolitik")


    @Composable
    fun PrivatPolitikOverview(navController: NavController) {


        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                PrivatPolitikTopBar(navController = navController)

            },
            content = {
                PrivatPolitikPage()
            }
        )
    }

    // top app bar til leveringssiden. den indeholder et ikon så det er muligt at gå tilbage.
    @Composable
    fun PrivatPolitikTopBar(navController: NavController) {
        TopAppBar(
            title = {

                MaterialText(
                    text = "Privatlivspolitik",
                    color = Color.Black,
                    fontSize = 30.sp
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate("profilStart") {
                        popUpTo("profilUI")
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

    @Composable
    fun PrivatPolitikPage() {
        val context = LocalContext.current
        LazyColumn(
            Modifier
                .background(Color(0xfffcfcf0))
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
          



            }

        }


    }







