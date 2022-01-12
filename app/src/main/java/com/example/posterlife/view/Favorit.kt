package com.example.posterlife.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

sealed class Favorit(val rute: String) {


    object FavoritStart : Favorit("favoritStart")

    @ExperimentalFoundationApi
    @Composable
    fun FavoritOverview(navigation: NavHostController) {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                FavoritTopBar()

            },
            content = {
                MainFavoritePage()
            }
        )
    }

    @Composable
    fun FavoritTopBar(){
        TopAppBar(
            title = {

                Text(
                    text = "Favoritter",
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
    fun MainFavoritePage() {
        Column(
            modifier = Modifier
                .background(Color(0xfffcfcf0))
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mine favoritter",
                fontSize = 30.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 5.dp)
            )

            LazyVerticalGrid(cells = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp), ) {

            }
        }

    }

}


