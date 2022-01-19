package com.example.posterlife.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.posterlife.model.Plakat
import com.example.posterlife.model.jsonParser.PlakatInfo
import com.example.posterlife.view.inspirationView.InspirationViewModel

/**
 * @Author Thamara Linnea (s205337), Kristoffer Pedersen (s205354)
 */

sealed class Favorit(val rute: String) {


    object FavoritStart : Favorit("favoritStart") {

        private val refresh = mutableStateOf(true)

        @ExperimentalFoundationApi
        @Composable
        fun FavoritOverview() {
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
        fun FavoritTopBar() {
            TopAppBar(
                title = {

                    Text(
                        text = "Favoritter",
                        color = Color.Black,
                        fontSize = 30.sp
                    )
                },
                actions = {
                   /* IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Filled.Favorite,
                            tint = Color.Red,
                            contentDescription = null
                        )
                    }*/

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
            val plakatInfo = PlakatInfo(context)
            val plakatHolder: ArrayList<Plakat> = plakatInfo.getPlakatInfo()
            val inspirationViewModel = InspirationViewModel()
            Column(
                Modifier
                    .background(Color(0xfffcfcf0))
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if(refresh.value) {
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                    ) {

                        items(inspirationViewModel.getFavorites(context).size) { index ->

                            Card(
                                modifier = Modifier
                                    .height(300.dp)
                                    .width(210.dp)
                                    .padding(
                                        top = 16.dp,
                                        start = 10.dp,
                                        end = 10.dp,
                                        bottom = 8.dp
                                    ),
                                elevation = 5.dp,
                                shape = RoundedCornerShape(4.dp),
                            ) {

                                Image(
                                    contentScale = ContentScale.Crop,
                                    painter = rememberImagePainter(
                                        data = plakatHolder.get(
                                            inspirationViewModel.getFavorites(
                                                context
                                            )[index]
                                        ).imageURL,
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable { }
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            Brush.verticalGradient(
                                                colors = listOf(Color.Transparent, Color.White),
                                            )
                                        )
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentAlignment = Alignment.BottomCenter
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.End,
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    ) {

                                        Box(modifier = Modifier.weight(1f)) {
                                        }
                                        Box() {
                                            FavoritButton(index = index)
                                        }

                                        Box(Modifier.padding(start = 4.dp, end = 4.dp)) {
                                            IconButton(
                                                onClick = { /*TODO*/ },
                                                modifier = Modifier.size(22.dp)
                                            ) {
                                                Icon(
                                                    Icons.Filled.ShoppingCart,
                                                    contentDescription = null,
                                                    Modifier.size(22.dp),
                                                    tint = Color.Black
                                                )
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }

        @Composable
        fun FavoritButton(
            color: Color = Color.Red,
            index: Int
        ) {
            var isFavorite by remember { mutableStateOf(true) }
            val inspirationViewModel: InspirationViewModel = viewModel()
            val context = LocalContext.current
            IconToggleButton(
                checked = isFavorite,
                modifier = Modifier.size(22.dp),
                onCheckedChange = {
                    isFavorite = !isFavorite
                    if (!isFavorite) {
                        inspirationViewModel.fjernFavorite(index, context)
                        refresh.value = false
                        refresh.value = true
                    }
                    if (!isFavorite) {
                        isFavorite = true
                    }
                }) {
                Icon(
                    tint = color, modifier = Modifier.size(22.dp), imageVector = if (isFavorite) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = null
                )
            }
        }

    }
}


