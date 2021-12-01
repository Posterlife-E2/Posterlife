package com.example.posterlife.UI

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.posterlife.JsonParser.PlakatInfo
import com.example.posterlife.Model.Plakat
import java.lang.NullPointerException
import java.util.*
import kotlin.collections.ArrayList

/**
 * @source https://developer.android.com/jetpack/compose/navigation
 */

sealed class Inspiration(val rute: String) {

    object InspirationStart : Inspiration("start") {



        @Composable
        fun InspirationMainUI(navController: NavController) {

            val plakatInfo = PlakatInfo()
            val plakatHolder: ArrayList<Plakat> = plakatInfo.getPlakatInfo()

            Column(modifier = Modifier
                .background(Color(0xfffcfcf0))
                .fillMaxHeight()
                .fillMaxWidth()
            )
            {
                    Image(
                        painter = rememberImagePainter(
                            data = plakatHolder.get(0).imageURL,
                            builder = {
                                transformations(CircleCropTransformation())
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(128.dp)
                    )
                }
        }
    }

    object InspirationOther : Inspiration("other") {

        @Composable
        fun InspirationOtherUI(navController: NavController) {
            Text("Get Scuffed")
        }
    }
}