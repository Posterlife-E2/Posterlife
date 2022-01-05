package com.example.posterlife.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import com.example.posterlife.R
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoEditorView

/**
 * @Source https://github.com/burhanrashid52/PhotoEditor
 * @Source https://github.com/Yalantis/uCrop
 * @Source https://github.com/godaddy/compose-color-picker
 */

sealed class BilledRedigering(var rute: String) {

    object BilledConfirm : BilledRedigering("billedConfirm") {

    }

    object BilledRed : BilledRedigering("billedRed") {

        private val visPopUp = mutableStateOf(false)


        @ExperimentalComposeUiApi
        @Composable
        fun BilledRedigering(/*billedSti: String*/) {


            val context = LocalContext.current

            val tekstFont = ResourcesCompat.getFont(context, R.font.roboto)

            val billedRedView = remember { PhotoEditorView(context) }
            billedRedView.source.setImageResource(R.drawable.test_image)

            val billedRedTool = remember { PhotoEditor.Builder(context, billedRedView) }
                .setPinchTextScalable(true)
                .setClipSourceImage(true)
                .setDefaultTextTypeface(tekstFont)
                .build()

            Column() {

                AndroidView(
                    factory = { billedRedView },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Row() {
                    TextButton(
                        onClick = {
                            if (billedRedTool.brushDrawableMode != true) {
                                billedRedTool.setBrushDrawingMode(true)
                            } else billedRedTool.setBrushDrawingMode(false)
                        },
                        modifier = Modifier
                            .padding(4.dp),
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Black, contentColor = Color.White
                        )
                    ) {
                        Text("Pensel")
                    }

                    TextButton(
                        onClick = {
                            visPopUp.value = true
                        },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Black, contentColor = Color.White
                        )
                    ) {
                        Text("Tekst")


                    }

                    var textFieldVal by remember { mutableStateOf("") }

                    if (visPopUp.value) {
                        var colorValg = 2147483647
                        AlertDialog(onDismissRequest = { visPopUp.value = false },
                            title = {
                                Text("Tilføj Tekst")
                            },

                            text = {
                                Column {

                                    TextField(
                                        value = textFieldVal,
                                        onValueChange = { textFieldVal = it })
                                    ClassicColorPicker(
                                        onColorChanged = { color: HsvColor ->
                                            colorValg = color.toColor().toArgb()
                                        },
                                        modifier = Modifier
                                            .height(300.dp)
                                            .padding(5.dp)
                                    )
                                }
                            },

                            confirmButton = {
                                Button(
                                    onClick = {
                                        if (!textFieldVal.equals("")) {

                                            val COLOR_BLACK = android.graphics.Color.parseColor("#000000")

                                            billedRedTool.addText(
                                                tekstFont,
                                                textFieldVal, colorValg
                                            )
                                            visPopUp.value = false
                                        }
                                    }
                                ) {
                                    Text("Accepter")
                                }


                            },
                            dismissButton = {
                                Button(
                                    onClick = {
                                        visPopUp.value = false
                                    }
                                ) {
                                    Text("Luk")
                                }

                            }

                        )
                    }
                }
            }
        }
    }
}