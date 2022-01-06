package com.example.posterlife.ui

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.toColor
import com.example.posterlife.R
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoEditorView
import ja.burhanrashid52.photoeditor.shape.ShapeBuilder

/**
 * @Source https://github.com/burhanrashid52/PhotoEditor
 * @Source https://github.com/Yalantis/uCrop
 * @Source https://github.com/godaddy/compose-color-picker
 */

sealed class BilledRedigering(var rute: String) {

    object BilledConfirm : BilledRedigering("billedConfirm") {

    }

    object BilledRed : BilledRedigering("billedRed") {

        private val visTekstPopUp = mutableStateOf(false)
        private val visPenselPopUp = mutableStateOf(false)

        private var penselSizeValueHolder = 1F
        private var switchPenselStateTemp = false

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


            Column(
                modifier = Modifier
                    .background(Color(0xfffcfcf0))
                    .fillMaxSize(),

                ) {

                BoxWithConstraints(
                    modifier = Modifier
                        .background(Color(0xfffcfcf0))
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter

                ) {
                    if (maxHeight < 700.dp) {
                        AndroidView(
                            factory = { billedRedView },
                            Modifier.width(350.dp),
                        )
                    } else {
                        AndroidView(
                            factory = { billedRedView },
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        onClick = {
                            visPenselPopUp.value = true
                        },
                        modifier = Modifier
                            .padding(4.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Black, contentColor = Color.White
                        )
                    ) {
                        Text("Pensel")
                    }
                    if (visPenselPopUp.value) {
                        PopUpPenselVindue(billedRedTool = billedRedTool, tekstFont = tekstFont)
                    }

                    TextButton(
                        modifier = Modifier
                            .padding(4.dp),
                        shape = RectangleShape,
                        onClick = {
                            visTekstPopUp.value = true
                        },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Black, contentColor = Color.White
                        )
                    ) {
                        Text("Tekst")
                    }
                    if (visTekstPopUp.value) {
                        PopUpTekstVindue(billedRedTool = billedRedTool, tekstFont = tekstFont)
                    }
                }
            }
        }

        @Composable
        private fun PopUpTekstVindue(billedRedTool: PhotoEditor, tekstFont: Typeface?) {
            var textFieldVal by remember { mutableStateOf("") }
            //-16777216 er sort i AARRBBGG farve koden.
            var colorValg = remember { -16777216 }
            AlertDialog(onDismissRequest = { visTekstPopUp.value = false },
                backgroundColor = Color(0xfffcfcf0),
                title = null,

                text = {
                    Column {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Tilføj Tekst",
                            fontSize = 18.sp
                        )
                        TextField(
                            value = textFieldVal,
                            onValueChange = { textFieldVal = it },
                            modifier = Modifier
                                .padding(4.dp),
                            textStyle = TextStyle(
                                fontSize = 20.sp,
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedLabelColor = Color(colorValg),
                                focusedIndicatorColor = Color(colorValg),
                                unfocusedLabelColor = Color(colorValg),
                                unfocusedIndicatorColor = Color(colorValg),
                                textColor = Color(colorValg),
                                cursorColor = Color(colorValg),
                                placeholderColor = Color.Gray
                            ),
                            placeholder = {
                                Text(text = "Indsæt tekst")
                            }
                        )
                        ClassicColorPicker(
                            onColorChanged = { color: HsvColor ->
                                colorValg = color.toColor().toArgb()

                                //Lille finte til at opdatere vores farve på textField uden at lave listeners og alt muligt halløj.
                                val textFieldTemp = textFieldVal
                                textFieldVal += "1"
                                textFieldVal = textFieldTemp
                            },
                            modifier = Modifier
                                .height(300.dp)
                                .padding(10.dp)
                        )
                    }
                },

                confirmButton = {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    )
                    {
                        TextButton(
                            onClick = {
                                if (!textFieldVal.equals("")) {
                                    billedRedTool.addText(
                                        tekstFont,
                                        textFieldVal, colorValg
                                    )
                                    textFieldVal = ""
                                    visTekstPopUp.value = false
                                }
                            },
                            modifier = Modifier
                                .offset(y = (-20).dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.textButtonColors(
                                backgroundColor = Color.Black, contentColor = Color.White
                            )

                        ) {
                            Text("Indsæt")
                        }
                    }
                }
            )
        }

        @Composable
        private fun PopUpPenselVindue(billedRedTool: PhotoEditor, tekstFont: Typeface?) {
            var penselSize by remember { mutableStateOf(1F) }
            var switchPenselState by remember { mutableStateOf(false) }

            penselSize = penselSizeValueHolder
            switchPenselState = switchPenselStateTemp

            AlertDialog(onDismissRequest = { visPenselPopUp.value = false },
                title = null,
                text = {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Pensel",
                                modifier = Modifier
                                    .padding(8.dp),
                                fontSize = 18.sp
                            )

                            Switch(
                                modifier = Modifier,
                                checked = switchPenselState,
                                onCheckedChange = {
                                    switchPenselState = it
                                    if (!switchPenselState) {
                                        billedRedTool.setBrushDrawingMode(false)
                                    } else if (switchPenselState) {
                                        billedRedTool.setBrushDrawingMode(true)
                                    }
                                    switchPenselStateTemp = switchPenselState
                                },
                                colors = SwitchDefaults.colors(

                                )
                            )
                        }


                        billedRedTool.brushDrawableMode
                        var colorValgPensel = remember { -16777216 }
                        Text(text = penselSize.toInt().toString() + "px")
                        Slider(
                            value = penselSize,
                            onValueChange = { penselSize = it },
                            valueRange = 1F..50F,
                            onValueChangeFinished = {
                                //Fjerner decimaler ved at udnytte hvordan ints og floats fungerer.
                                penselSize = penselSize.toInt().toFloat()
                                billedRedTool.brushSize = penselSize
                                penselSizeValueHolder = penselSize
                            },
                            colors = SliderDefaults.colors(
                                thumbColor = Color(colorValgPensel),
                                activeTrackColor = Color(colorValgPensel)
                            )
                        )
                        ClassicColorPicker(
                            onColorChanged = { color: HsvColor ->
                                colorValgPensel = color.toColor().toArgb()
                                billedRedTool.brushColor = colorValgPensel

                                //Presser den til at skifte farve.
                                val penselSizeTemp = penselSize
                                penselSize += 1
                                penselSize = penselSizeTemp
                            },
                            modifier = Modifier
                                .height(300.dp)
                                .padding(10.dp)
                        )


                    }
                },
                confirmButton = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        TextButton(
                            onClick = { visPenselPopUp.value = false },
                            modifier = Modifier
                                .offset(y = (-20).dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.textButtonColors(
                                backgroundColor = Color.Black, contentColor = Color.White
                            )
                        ) {
                            Text("Accepter")
                        }
                    }
                })
        }
    }
}