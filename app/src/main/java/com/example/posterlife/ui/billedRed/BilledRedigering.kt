package com.example.posterlife.ui.billedRed

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import com.example.posterlife.R
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor
import ja.burhanrashid52.photoeditor.OnSaveBitmap
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoEditorView
import ja.burhanrashid52.photoeditor.PhotoFilter
import android.graphics.Bitmap
import android.util.Log
import androidx.annotation.NonNull
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.view.drawToBitmap
import java.lang.Exception


/**
 * @Author Kristoffer Pedersen s205354
 *
 * @Source https://github.com/burhanrashid52/PhotoEditor
 * @Source https://github.com/Yalantis/uCrop
 * @Source https://github.com/godaddy/compose-color-picker
 *
 * Material Sources
 * @Source https://foso.github.io/Jetpack-Compose-Playground/foundation/lazyrow/
 */

sealed class BilledRedigering(var rute: String) {

    object BilledConfirm : BilledRedigering("billedConfirm/{billedURI}") {

        @Composable
        fun BilledConfirm(billedURI: String?){

            Text(text = "test")
        }
    }

    object BilledRed : BilledRedigering("billedRed") {

        private val visTekstPopUp = mutableStateOf(false)
        private val visPenselPopUp = mutableStateOf(false)
        val visFilterMenu = mutableStateOf(false)

        private val gemBillede = mutableStateOf(false)

        private var penselSizeValueHolder = 25F
        private var switchPenselStateTemp = false
        private var penselColorState = -16777216
        var filterValg = PhotoFilter.NONE

        @ExperimentalComposeUiApi
        @Composable
        fun BilledRedigering(/*billedSti: String*/) {

            val billedURI = R.drawable.test_image

            val context = LocalContext.current

            val tekstFont = ResourcesCompat.getFont(context, R.font.roboto)

            val billedRedView = remember { mutableStateOf(PhotoEditorView(context)) }
            billedRedView.value.source.setImageResource(billedURI)

            val billedRedTool =
                remember { PhotoEditor.Builder(context, billedRedView.value) }
                    .setPinchTextScalable(true)
                    .setClipSourceImage(true)
                    .setDefaultTextTypeface(tekstFont)
                    .build()

            var eraserState by remember { mutableStateOf(false) }

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
                            factory = { billedRedView.value },
                            Modifier.width(350.dp),
                        )
                    } else {
                        AndroidView(
                            factory = { billedRedView.value }
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        modifier = Modifier
                            .padding(4.dp),
                        shape = RectangleShape,
                        onClick = {
                            billedRedTool.undo()

                        },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Black, contentColor = Color.White
                        )
                    ) {
                        Text("Undo")
                    }

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
                        PopUpPenselVindue(billedRedTool = billedRedTool)
                    }

                    val eraserKnapFarve = remember { MutableInteractionSource() }
                    val eraserFarve = if (!eraserState) Color.Black else Color(0xff239023)
                    Button(
                        onClick = {
                            if (!eraserState) {
                                billedRedTool.brushEraser()
                                billedRedTool.brushSize = 100F
                                eraserState = true
                            } else {
                                billedRedTool.setBrushDrawingMode(false)
                                switchPenselStateTemp = false
                                eraserState = false
                            }
                        }, modifier = Modifier
                            .padding(4.dp),
                        shape = RectangleShape,
                        interactionSource = eraserKnapFarve,
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = eraserFarve, contentColor = Color.White
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.eraser),
                            contentDescription = "",
                            Modifier.size(20.dp)
                        )
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
                        switchPenselStateTemp = false
                    }
                    TextButton(
                        modifier = Modifier
                            .padding(4.dp),
                        shape = RectangleShape,
                        onClick = {
                            visFilterMenu.value = true
                        },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Black, contentColor = Color.White
                        )
                    ) {
                        Text("Filter")
                    }
                    if (visFilterMenu.value) {
                        val billedFilterShower = BilledFilterShower(billedURI)
                        billedFilterShower.BilledFilter()
                    }
                    billedRedTool.setFilterEffect(filterValg)



                    TextButton(
                        onClick = { gemBillede.value = true },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Black, contentColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(4.dp),
                        shape = RectangleShape,
                    ) {
                        Text("Gem")
                    }
                    if(gemBillede.value){
                        GemBilled(billedRedTool, billedRedView)
                    }
                }
            }
        }

        //Keyboard håndtering source: https://stackoverflow.com/questions/59133100/how-to-close-the-virtual-keyboard-from-a-jetpack-compose-textfield
        @ExperimentalComposeUiApi
        @Composable
        private fun PopUpTekstVindue(billedRedTool: PhotoEditor, tekstFont: Typeface?) {
            var textFieldVal by remember { mutableStateOf("") }
            //-16777216 er en sort farve i AARRBBGG farve koden.
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
        private fun PopUpPenselVindue(billedRedTool: PhotoEditor) {
            var penselSize by remember { mutableStateOf(0F) }
            var colorValgPensel: Int
            var switchPenselState by remember { mutableStateOf(false) }
            colorValgPensel = penselColorState
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
                                modifier = Modifier
                                    .offset(y = (-3).dp),
                                checked = switchPenselState,
                                onCheckedChange = {
                                    switchPenselState = it
                                    if (!switchPenselState) {
                                        billedRedTool.setBrushDrawingMode(false)
                                    } else if (switchPenselState) {
                                        billedRedTool.setBrushDrawingMode(true)
                                    }
                                    switchPenselStateTemp = switchPenselState
                                    billedRedTool.brushSize = penselSize
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color(colorValgPensel),
                                    uncheckedThumbColor = Color.LightGray
                                )
                            )
                        }

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
                            color = Color(colorValgPensel),
                            onColorChanged = { color: HsvColor ->
                                colorValgPensel = color.toColor().toArgb()
                                billedRedTool.brushColor = colorValgPensel

                                penselColorState = colorValgPensel
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

        @Composable
        private fun GemBilled(billedRedTool: PhotoEditor, billedRedView: MutableState<PhotoEditorView>) {



            billedRedTool.saveAsBitmap(object : OnSaveBitmap {
                override fun onBitmapReady(@NonNull  saveBitmap: Bitmap?) {
                    Log.e("BilledRedigering", "Billed gemt til BitMap")

                    saveBitmap
                }

                override fun onFailure(@NonNull exception: Exception?) {
                    Log.e("BilledRedigering", "billed gemmefunktion fejlede")
                }
            }
            )

            gemBillede.value = false

        }
    }
}

