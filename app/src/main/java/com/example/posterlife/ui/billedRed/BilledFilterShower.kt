package com.example.posterlife.ui.billedRed

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
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
import com.example.posterlife.R
import com.example.posterlife.ui.billedRed.BilledRedigering.BilledRed.filterValg
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoEditorView
import ja.burhanrashid52.photoeditor.PhotoFilter

class BilledFilterShower(private val billedURI: Int) {

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

    private val tempFilter = filterValg

    @ExperimentalComposeUiApi
    @Composable
    fun BilledFilter(billedRedTool: PhotoEditor) {

        val context = LocalContext.current

        val billedTempRedView = PhotoEditorView(context)
        billedTempRedView.source.setImageResource(billedURI)

        //Opretter et nyt midlertidigt view, så vi kan have et repræsentivt billede.
        val view = View(context)

        //Skaber et nyt view..
        billedTempRedView.addView(view)

        val billedTempRedTool =
            remember { PhotoEditor.Builder(context, billedTempRedView) }
                .setPinchTextScalable(true)
                .setClipSourceImage(true)
                .build()



        AlertDialog(
            onDismissRequest = { BilledRedigering.BilledRed.visFilterMenu.value = false },
            properties = DialogProperties(usePlatformDefaultWidth = false),
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xfffcfcf0)),
            title = null,
            text = {
                BoxWithConstraints(
                    modifier = Modifier
                        .background(Color(0xfffcfcf0))
                        .fillMaxSize(),
                    contentAlignment = Alignment.TopCenter

                ) {
                    //Opretter billedet alt efter hvor stor mobilen er.
                    if (maxHeight < 700.dp) {
                        AndroidView(
                            factory = { billedTempRedView },
                            Modifier.width(350.dp),
                        )
                    } else {
                        AndroidView(
                            factory = { billedTempRedView }
                        )
                    }
                }

                billedTempRedTool.setFilterEffect(filterValg)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(4.dp)
                    ) {
                        items(filtre.size) { index ->
                            Button(onClick = {
                                billedTempRedTool.setFilterEffect(filtre.get(index))
                                filterValg = filtre.get(index)
                            }) {
                                Text("test")
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        BilledRedigering.BilledRed.visFilterMenu.value = false },
                    shape = RectangleShape,
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.Black, contentColor = Color.White
                    )
                ) {
                    Text("Accepter")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        filterValg = tempFilter
                        BilledRedigering.BilledRed.visFilterMenu.value = false
                    },
                    shape = RectangleShape,
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.Black, contentColor = Color.White
                    )
                ) {
                    Text("Luk")
                }
            })
    }
}