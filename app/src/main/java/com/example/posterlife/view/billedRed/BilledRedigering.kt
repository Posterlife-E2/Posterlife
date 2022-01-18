package com.example.posterlife.view.billedRed

import android.content.Context
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
import com.example.posterlife.R
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.annotation.NonNull
import androidx.compose.foundation.Image
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.posterlife.saveImageController.UploadImage
import com.example.posterlife.view.NavigationBundNav
import java.lang.Exception
import android.provider.MediaStore.Images
import android.text.Editable
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.ui.engine.geometry.Outline
import com.example.posterlife.view.loginUI.Login
import ja.burhanrashid52.photoeditor.*
import java.io.ByteArrayOutputStream


/**
 * @Author Kristoffer Pedersen s205354
 *
 * @Source https://github.com/burhanrashid52/PhotoEditor
 * @Source https://github.com/godaddy/compose-color-picker
 * @Source https://colinyeoh.wordpress.com/2012/05/18/android-getting-image-uri-from-bitmap/
 *
 * Diverse
 * @Source https://developer.android.com/reference/kotlin/android/view/inputmethod/InputMethodManager
 * @Source https://stackoverflow.com/questions/8035107/how-to-set-cursor-position-in-edittext
 *
 * Material Sources
 * @Source https://foso.github.io/Jetpack-Compose-Playground/foundation/lazyrow/
 *
 * XML baseret AlertDialog Builder
 * @Source https://developer.android.com/reference/android/app/AlertDialog.Builder
 *
 */

sealed class BilledRedigering(var rute: String) : ViewModel() {

    object BilledConfirm : BilledRedigering("billedConfirm") {

        @ExperimentalCoilApi
        @Composable
        fun BilledConfirm(billedViewModel: BilledViewModel, navController: NavController) {

            val savedUri = billedViewModel.getBilledURI()
            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

            Scaffold(
                scaffoldState = scaffoldState,
                //topBar = {
                    //TopBar("Foto Baggrund")
                //},
                content = {
                    Image(
                        painter = rememberImagePainter(data = savedUri),
                        contentDescription = "fotoKamera - Billed som blev taget.",
                        Modifier
                            .shadow(elevation = 20.dp, shape = RectangleShape, clip = true)
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    /*Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xfffcfcf0)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Box(modifier = Modifier.padding(top = 40.dp)) {
                            Image(
                                painter = rememberImagePainter(data = savedUri),
                                contentDescription = "fotoKamera - Billed som blev taget.",
                                Modifier
                                    .shadow(elevation = 20.dp, shape = RectangleShape, clip = true)
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }*/
                },
                bottomBar = {
                    AcceptPictureBottomBar(navController)
                }
            )

        }
    }

    @Composable
    fun TopBar(title: String) {
        TopAppBar(
            title = {

                Text(
                    text = title,
                    color = Color.Black,
                    fontSize = 30.sp
                )
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            },

            backgroundColor = Color(0xfffcfcf0),

            //elevation = 12.dp
        )

    }

    @Composable
    fun AcceptPictureBottomBar(navController: NavController) {

        Row(
            modifier =
            Modifier
                .background(MaterialTheme.colors.onPrimary.copy(0.5f))
                .height(55.dp)
        ) {
            IconButton(
                onClick = { navController.navigate(NavigationBundNav.Kamera.route) },
                modifier = Modifier.align(Alignment.CenterVertically)

            ) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Box(modifier = Modifier.weight(1f)) {
            }
            Text(text = "VÆLG BILLEDE", color = Color.White, fontSize = 25.sp, modifier = Modifier.align(Alignment.CenterVertically))
            Box(modifier = Modifier.weight(1f)) {
            }
            IconButton(
                onClick = { navController.navigate("billedRed") },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    Icons.Filled.Done,
                    contentDescription = null,
                    tint = Color.White,

                )
            }
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
        fun BilledRedigering(billedViewModel: BilledViewModel, navController: NavController) {

            val context = LocalContext.current

            val savedBilledURI = billedViewModel.getBilledURI()

            val tekstFont = ResourcesCompat.getFont(context, R.font.roboto)

            val billedRedView = remember { mutableStateOf(PhotoEditorView(context)) }
            billedRedView.value.source.setImageURI(savedBilledURI)

            val billedRedTool =
                remember { PhotoEditor.Builder(context, billedRedView.value) }
                    .setPinchTextScalable(true)
                    .setClipSourceImage(false)
                    .setDefaultTextTypeface(tekstFont)
                    .build()

            var eraserState by remember { mutableStateOf(false) }

            billedRedTool.setOnPhotoEditorListener(object : OnPhotoEditorListener {
                override fun onEditTextChangeListener(
                    rootView: View,
                    text: String?,
                    colorCode: Int
                ) {
                    //Da vi ikke kan bruge compose med det her bibliotek der bliver brugt til redigering,
                    //så må vi være lidt opfindsomme. Koden her åbner en AlertDialog widget som man ikke kan se
                    //med en EditText i, som fungere som mellemmand mellem den tekst vi skal redigere og det som
                    //brugeren indtaster.

                    val tekstInput = EditText(context)
                    tekstInput.requestFocus()

                    val alertDialog = android.app.AlertDialog.Builder(context)
                    tekstInput.text = Editable.Factory.getInstance().newEditable(text)
                    alertDialog.create()
                    tekstInput.background.clearColorFilter()
                    alertDialog.setView(tekstInput)

                    val alertSetWindow = alertDialog.show()
                    alertSetWindow.window?.setBackgroundDrawableResource(R.drawable.transparent)
                    alertSetWindow.window?.setLayout(1, 1)
                    alertSetWindow.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

                    tekstInput.setSelection(tekstInput.length())

                    val keyboard =
                        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    keyboard.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)

                    tekstInput.addTextChangedListener {
                        billedRedTool.editText(rootView, tekstInput.text.toString(), colorCode)
                    }
                }

                override fun onAddViewListener(viewType: ViewType?, numberOfAddedViews: Int) {
                    //Skal ikke bruges til noget, men er påkrævet overriden pga. anonymous object.
                }

                override fun onRemoveViewListener(
                    viewType: ViewType?,
                    numberOfAddedViews: Int
                ) {
                    //Skal ikke bruges til noget, men er påkrævet overriden pga. anonymous object.
                }

                override fun onStartViewChangeListener(viewType: ViewType?) {
                    //Skal ikke bruges til noget, men er påkrævet overriden pga. anonymous object.
                }

                override fun onStopViewChangeListener(viewType: ViewType?) {
                    //Skal ikke bruges til noget, men er påkrævet overriden pga. anonymous object.
                }

                override fun onTouchSourceImage(event: MotionEvent?) {
                    //Skal ikke bruges til noget, men er påkrævet overriden pga. anonymous object.
                }

            })


            Scaffold(
                //topBar = {
                    //TopBar(title = "Rediger")
               // },

                content = {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colors.primary)
                            .fillMaxSize(),

                        ) {
                        BoxWithConstraints(
                            modifier = Modifier
                                .background(MaterialTheme.colors.primary)
                                .weight(1f),
                            contentAlignment = Alignment.Center,

                        ) {
                            if (maxHeight < 700.dp) {
                                AndroidView(
                                    factory = { billedRedView.value },
                                    Modifier
                                        .scale(0.9f)
                                        .shadow(elevation = 20.dp, shape = RectangleShape, clip = true),

                                )
                            } else {
                                AndroidView(
                                    factory = { billedRedView.value },
                                    Modifier
                                        .fillMaxSize()

                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.Bottom

                            ) {
                            Box(
                                modifier = Modifier
                                    .background(Color.DarkGray)
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .border(1.dp, Color.Black, shape = RectangleShape),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable { (billedRedTool.undo()) },
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        Icons.Filled.Undo,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(30.dp)
                                    )
                                    Text(text = "Undo", color = Color.White)
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .background(Color.Black)
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .border(1.dp, Color.Black, shape = RectangleShape)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable { visPenselPopUp.value = true },
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        Icons.Filled.Brush,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(30.dp)
                                    )
                                    Text(text = "Pensel", color = Color.White)

                                }
                            }
                            if (visPenselPopUp.value) {
                                PopUpPenselVindue(billedRedTool = billedRedTool)
                            }

                            Box(
                                modifier = Modifier
                                    .background(Color.DarkGray)
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .border(1.dp, Color.Black, shape = RectangleShape)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            if (!eraserState) {
                                                billedRedTool.brushEraser()
                                                billedRedTool.brushSize = 100F
                                                eraserState = true
                                            } else {
                                                billedRedTool.setBrushDrawingMode(false)
                                                switchPenselStateTemp = false
                                                eraserState = false
                                            }
                                        },
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                )
                                {
                                    Icon(
                                        Icons.Filled.PhonelinkErase,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(30.dp)
                                    )
                                    Text(text = "Erase", color = Color.White)
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .background(Color.Black)
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .border(1.dp, Color.Black, shape = RectangleShape)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable { visTekstPopUp.value = true },
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        Icons.Filled.Title,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(30.dp)
                                    )
                                    Text(text = "Tekst", color = Color.White)
                                }
                            }
                            if (visTekstPopUp.value) {
                                PopUpTekstVindue(
                                    billedRedTool = billedRedTool,
                                    tekstFont = tekstFont
                                )
                                switchPenselStateTemp = false
                            }
                            Box(
                                modifier = Modifier
                                    .background(Color.DarkGray)
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .border(1.dp, Color.Black, shape = RectangleShape),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable { visFilterMenu.value = true },
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        Icons.Filled.Filter,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(30.dp)
                                    )
                                    Text(text = "Filter", color = Color.White)
                                }
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(Color.DarkGray)
                        )

                        if (visFilterMenu.value) {
                            val billedFilterShower = BilledFilterShower(savedBilledURI)
                            billedFilterShower.BilledFilter()
                        }

                        billedRedTool.setFilterEffect(filterValg)
                        if (gemBillede.value) {
                            GemBilled(billedRedTool, navController)
                        }
                    }
                },
                bottomBar = {
                    BottomAppBar(
                        backgroundColor = Color.DarkGray
                    ) {
                        IconButton(onClick = { navController.navigate(NavigationBundNav.Kamera.route) }) {
                            Icon(
                                Icons.Filled.Close,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                        Box(modifier = Modifier.weight(1f)) {
                        }
                        Text(text = "GEM BILLEDE", color = Color.White, fontSize = 25.sp)
                        Box(modifier = Modifier.weight(1f)) {
                        }
                        IconButton(onClick = { gemBillede.value = true }) {
                            Icon(
                                Icons.Filled.Done,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
            )

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
                backgroundColor = Color(0xfffcfcf0),
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
        private fun GemBilled(
            billedRedTool: PhotoEditor,
            navController: NavController
        ) {

            val context = LocalContext.current

            billedRedTool.saveAsBitmap(object : OnSaveBitmap {
                override fun onBitmapReady(@NonNull saveBitmap: Bitmap?) {
                    Log.e("BilledRedigering", "Billed gemt til BitMap")

                    val uri = saveBitmap?.let { getImageUri(context, it) }

                    if (uri != null) {
                        uploadBillede(uri, context)
                        navController.navigate(NavigationBundNav.MineDesign.route) {
                            //sletter backstack indtil login.page
                            popUpTo(Login.LoginScreen.route)
                        }
                    }
                }

                override fun onFailure(@NonNull exception: Exception?) {
                    Log.e("BilledRedigering", "billed gemmefunktion fejlede")
                }
            }
            )

            gemBillede.value = false

        }

        //URI løsning fra https://colinyeoh.wordpress.com/2012/05/18/android-getting-image-uri-from-bitmap/
        fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
            val bytes = ByteArrayOutputStream()
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path = Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
            return Uri.parse(path)
        }

        private fun uploadBillede(savedUri: Uri, context: Context) {
            UploadImage.uploadImage(savedUri, context)
        }
    }
}