package com.example.posterlife.view.billedRed

import android.view.View
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.DialogProperties
import com.example.posterlife.view.billedRed.BilledRedigering.BilledRed.filterValg
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoEditorView
import ja.burhanrashid52.photoeditor.PhotoFilter
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.ui.unit.sp

/**
 * @Author Kristoffer Pedersen s205354
 *
 * @Source @Source https://github.com/burhanrashid52/PhotoEditor
 */

class BilledFilterShower(private val billedURI: Uri) {

    private val filtre: List<PhotoFilter> = listOf(
        PhotoFilter.NONE,
        PhotoFilter.BRIGHTNESS,
        PhotoFilter.AUTO_FIX,
        PhotoFilter.BLACK_WHITE,
        PhotoFilter.CONTRAST,
        PhotoFilter.CROSS_PROCESS,
        PhotoFilter.DOCUMENTARY,
        PhotoFilter.DUE_TONE,
        PhotoFilter.FILL_LIGHT,
        PhotoFilter.FISH_EYE,
        PhotoFilter.FLIP_HORIZONTAL,
        PhotoFilter.FLIP_VERTICAL,
        PhotoFilter.GRAIN,
        PhotoFilter.GRAY_SCALE,
        PhotoFilter.LOMISH,
        PhotoFilter.NEGATIVE,
        PhotoFilter.POSTERIZE,
        PhotoFilter.SATURATE,
        PhotoFilter.SEPIA,
        PhotoFilter.SHARPEN,
        PhotoFilter.TEMPERATURE,
        PhotoFilter.TINT,
        PhotoFilter.VIGNETTE,
        PhotoFilter.ROTATE
    )

    private val filterNavn: List<String> = listOf(
        "Ingen",
        "Lys",
        "Auto",
        "Sort/Hvid",
        "Contrast",
        "Cross",
        "Dokumentar",
        "Gul",
        "Fyldt lys",
        "Oval",
        "FlipH",
        "FlipV",
        "Gryn",
        "Lusket",
        "Grå",
        "Negativ",
        "Positiv",
        "Farve",
        "Sepia",
        "Skarp",
        "Temperatur",
        "Tint",
        "Vignet",
        "Roter"
    )

    private val tempFilter = filterValg

    @ExperimentalComposeUiApi
    @Composable
    fun BilledFilter() {

        val context = LocalContext.current

        val billedTempRedView = PhotoEditorView(context)
        billedTempRedView.source.setImageURI(billedURI)

        //Opretter et nyt midlertidigt view, så vi kan have et repræsentivt billede.
        val view = View(context)

        //Skaber et nyt view..
        billedTempRedView.addView(view)

        val billedTempRedTool =
            remember { PhotoEditor.Builder(context, billedTempRedView) }
                .setPinchTextScalable(true)
                .setClipSourceImage(false)
                .build()


        AlertDialog(
            onDismissRequest = { BilledRedigering.BilledRed.visFilterMenu.value = false },
            properties = DialogProperties(usePlatformDefaultWidth = false),
            modifier = Modifier
                .fillMaxSize(),
            backgroundColor = Color.DarkGray,
            title = null,
            text = {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.TopCenter

                ) {
                    //Opretter billedet alt efter hvor stor mobilen er.
                    AndroidView(
                        factory = { billedTempRedView }
                    )
                }

                billedTempRedTool.setFilterEffect(filterValg)

            },
            confirmButton = {

                Column() {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        items(filtre.size) { index ->

                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(80.dp)
                                    .border(1.dp, Color.White, shape = RectangleShape)
                                    .background(color = Color.Black)
                                    .padding(end = 3.dp)
                                    .clickable(onClick = {
                                        billedTempRedTool.setFilterEffect(filtre.get(index))
                                        filterValg = filtre.get(index)
                                    })) {

                                Column(modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center) {
                                    Text(filterNavn[index], color = Color.White)
                                }
                            }
                        }
                    }

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                        IconButton(onClick = { filterValg = tempFilter
                            BilledRedigering.BilledRed.visFilterMenu.value = false}) {
                            Icon(
                                Icons.Filled.Close,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                        Text(text = "VÆLG FILTER", color = Color.White, fontSize = 20.sp)

                        IconButton(onClick = { BilledRedigering.BilledRed.visFilterMenu.value = false }) {
                            Icon(
                                Icons.Filled.Done,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }

                }
            }
        )
    }
}