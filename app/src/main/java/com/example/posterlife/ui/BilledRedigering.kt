package com.example.posterlife.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import com.example.posterlife.R
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoEditorView

/**
 * @Source https://github.com/burhanrashid52/PhotoEditor
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

                AndroidView(factory = { billedRedView })
                {
                    billedRedView.apply {

                    }

                }

                Row() {
                    TextButton(
                        onClick = {
                            if (billedRedTool.brushDrawableMode != true) {
                                billedRedTool.setBrushDrawingMode(true)
                            } else billedRedTool.setBrushDrawingMode(false)


                        },
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

                    var textFieldVal by remember { mutableStateOf("")}

                    if (visPopUp.value) {
                        AlertDialog(onDismissRequest = { visPopUp.value = false },
                            title = {
                                Text("Tilføj Tekst")
                            },

                            text = {
                                Column {

                                    TextField(
                                        value = textFieldVal,
                                        onValueChange = { textFieldVal = it })
                                }
                            },

                            confirmButton = {
                                Button(
                                    onClick = {
                                        billedRedTool.addText(
                                            tekstFont,
                                            textFieldVal, 1933211111
                                        )
                                        visPopUp.value = false
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