package com.example.posterlife.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.posterlife.model.Plakat
import com.example.posterlife.model.jsonParser.PlakatInfo
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader

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



    @ExperimentalCoilApi
    @ExperimentalFoundationApi
    @Composable
    fun MainFavoritePage() {
        val context = LocalContext.current
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
                val indexList: List<String> = File("index.txt").bufferedReader().readLines()
                val plakatInfo = PlakatInfo(context)
                val plakatHolder: ArrayList<Plakat> = plakatInfo.getPlakatInfo()

                items(indexList.size) {index ->

                    Image(
                        painter = rememberImagePainter(
                            data = plakatHolder.get(index).imageURL,
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(300.dp)

                    )
                }
            }
        }

    }

}


