package com.example.posterlife.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.posterlife.JsonParser.PlakatInfo
import com.example.posterlife.Model.Plakat
import kotlin.collections.ArrayList

/**
 * @source https://developer.android.com/jetpack/compose/navigation
 *
 * Ting til at lave ting.
 * https://juliensalvi.medium.com/parallax-effect-made-it-simple-with-jetpack-compose-d19bde5688fc
 */

sealed class Inspiration(val rute: String) {

    object InspirationStart : Inspiration("start") {

        @Composable
        fun InspirationOverview(navController: NavController) {

            val context = LocalContext.current
            val plakatInfo = PlakatInfo(context)
            val plakatHolder: ArrayList<Plakat> = plakatInfo.getPlakatInfo()

            Column(
                Modifier
                    .background(Color(0xfffcfcf0))
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Plakater",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                LazyColumn(
                    Modifier
                        .background(Color(0xfffcfcf0))
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(plakatHolder.size) { index ->
                        Image(
                            painter = rememberImagePainter(
                                data = plakatHolder.get(index).imageURL,
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(500.dp)
                                .padding(16.dp)
                                .clickable {
                                    navController.navigate("focusImage/$index")
                                }
                        )
                        Text(
                            plakatHolder.get(index).title,
                            modifier = Modifier
                                .padding(16.dp),

                            )
                    }
                }
            }
        }
    }
    object InspirationFocusImage : Inspiration("focusImage/{plakatIndex}") {

        @Composable
        fun InspirationFocusImage(plakatIndex: Int?) {

            val context = LocalContext.current
            val plakatInfo = PlakatInfo(context)
            val plakatHolder = plakatIndex?.let { plakatInfo.getPlakatInfo().get(it) }

            if (plakatHolder != null) {

                //SKRIV KODE HER

                Text(plakatHolder.title)
            }
        }
    }
}