package com.example.posterlife.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class Favorit(val rute: String) {

    object FavoritStart: Favorit("favoritStart")

    @Composable
    fun FavoritTopBar() {
        Scaffold(

            topBar = {
                TopAppBar(
                    title = {

                        Text(
                            text = "Inspiration",
                            color = Color.Black,
                            fontSize = 30.sp
                        )
                    },
                    actions = {

                        IconButton(onClick = { }) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = null
                            )
                        }
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
        ) {
            // MainFavoritePage(navigation =  )
            }
        }
    }


@Composable
fun MainFavoritePage(navigation: Navigation) {
    Column( modifier = Modifier
        .background(Color(0xfffcfcf0))
        .fillMaxWidth()
        .fillMaxHeight()) {
        Text(text = "Favoritter", fontSize = 30.sp)
    }
    
}
