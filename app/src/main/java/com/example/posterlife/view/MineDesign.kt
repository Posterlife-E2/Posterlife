package com.example.posterlife.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.posterlife.R
import com.example.posterlife.saveImageController.UploadImage
import java.io.File

/**
 * @Author M-Najib Hebrawi (s181663), Thamara Linnea (s205337), Camilla Bøjden (s205360)
 * @source https://developer.android.com/jetpack/compose/navigation
 * Ting til at lave ting.
 * https://juliensalvi.medium.com/parallax-effect-made-it-simple-with-jetpack-compose-d19bde5688fc
 * https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example
 */
sealed class MineDesign(val route: String) {

    object MineDesignStart : MineDesign("MineDesignStart") {
        var mineDesignData = ArrayList<String>()
        private val permissions = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        @ExperimentalComposeUiApi
        @ExperimentalFoundationApi
        @ExperimentalCoilApi
        @androidx.compose.runtime.Composable
        fun MineDesignStart(navController: NavController
        ) {
            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    MineDesignTopBar(navController)
                },
                content = {
                    MineDesignContent()
                }
            )
        }

        @Composable
        fun MineDesignTopBar(navController: NavController) {
            TopAppBar(
                title = {
                    Text(
                        text = "Mine Design",
                        color = Color.Black,
                        fontSize = 30.sp
                    )
                },
                actions = {
                    /*IconButton(onClick = { navController.navigate("Favorit"){
                        popUpTo(NavigationBundNav.MineDesign.route)
                    } }) {
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

        // variabel der skal bruges til at refreshe Mine design når et billede bliver slettet.
        private val refreshGrid = mutableStateOf(true)

        @ExperimentalCoilApi
        @ExperimentalComposeUiApi
        @ExperimentalFoundationApi
        @Composable
        fun MineDesignContent() {
            val context = LocalContext.current
            ActivityCompat.requestPermissions(
                context as Activity,
                permissions, 0
            )
            val file = File(context.getOutputDirectory(), "Files.txt")
            if (file.exists()) {
                val theFile = File(context.getOutputDirectory(), "Files.txt")
                val text = theFile.readText()
                val result: List<String> = text.split(",").map { it.trim() }
                Log.d("Data", result.toString())
                if (result.isNotEmpty()) {
                    Column(
                        Modifier
                            .background(Color(0xfffcfcf0))
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val openDialog = remember { mutableStateOf(false) }
                        if (refreshGrid.value) {
                            LazyVerticalGrid(
                                cells = GridCells.Fixed(2),
                                contentPadding = PaddingValues(8.dp)
                            )
                            {
                                items(result.size) { index ->
                                    val source =
                                        "content://media/external/images/media/" + result[index]

                                    Card(
                                        modifier = Modifier
                                            .padding(8.dp),
                                        elevation = 10.dp
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(start = 3.dp, end = 3.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier.height(30.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                EditTitle()
                                                Box(modifier = Modifier.weight(1f))
                                                Box() {
                                                    IconButton(
                                                        onClick = { openDialog.value = true
                                                                  },
                                                        modifier = Modifier.size(22.dp)
                                                    ) {
                                                        Icon(
                                                            Icons.Filled.DeleteOutline,
                                                            contentDescription = null,
                                                            Modifier.size(22.dp)
                                                        )
                                                    }
                                                }
                                            }
                                            Image(
                                                painter = rememberImagePainter(
                                                    data = Uri.parse(source)
                                                ),
                                                contentDescription = "Image",
                                                modifier = Modifier
                                                    .size(250.dp)
                                                    .background(Color.White)
                                            )
                                            Row(
                                                modifier = Modifier.height(30.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Box(modifier = Modifier.clickable { }) {
                                                    Row() {
                                                        Text(
                                                            text = "Rediger",
                                                            fontWeight = FontWeight.Light
                                                        )
                                                        Box() {
                                                            Icon(
                                                                Icons.Filled.Edit,
                                                                contentDescription = null,
                                                                Modifier.size(22.dp)
                                                            )
                                                        }
                                                    }
                                                }
                                                Box(modifier = Modifier.weight(1f))
                                                Box() {
                                                    IconButton(
                                                        onClick = { /*TODO*/ },
                                                        modifier = Modifier.size(22.dp)
                                                    ) {
                                                        Icon(
                                                            Icons.Filled.Send,
                                                            contentDescription = null,
                                                            Modifier.size(22.dp)
                                                        )
                                                    }
                                                }
                                                Box(
                                                    modifier = Modifier
                                                        .size(22.dp)
                                                ) {
                                                    FavoritButton()
                                                }
                                                Box() {
                                                    IconButton(
                                                        onClick = { /*TODO*/ },
                                                        modifier = Modifier.size(22.dp)
                                                    ) {
                                                        Icon(
                                                            Icons.Filled.AddShoppingCart,
                                                            contentDescription = null,
                                                            Modifier.size(22.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                        if (openDialog.value) {
                                            AlertDialog(
                                                onDismissRequest = {
                                                    openDialog.value = false
                                                },
                                                title = {
                                                    Text(text = "Slet Plakat")
                                                },
                                                text = {
                                                    Text("Er du sikker på at du vil slette denne plakat?")
                                                },
                                                confirmButton = {
                                                    Button(
                                                        onClick = {
                                                            openDialog.value = false
                                                            readsPost(
                                                                context,
                                                                result[index],
                                                                true
                                                            )
                                                        }) {
                                                        Text("Ja")
                                                    }
                                                },
                                                dismissButton = {
                                                    Button(
                                                        onClick = {
                                                            openDialog.value = false

                                                        }
                                                    ) {
                                                        Text("Afbryd")
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        refreshGrid.value = false
                        refreshGrid.value = true
                    }
                } else {
                    Column(
                        Modifier
                            .background(Color(0xfffcfcf0))
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "No Mine Design",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            } else {
                Column(
                    Modifier
                        .background(Color(0xfffcfcf0))
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "No Mine Design",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        fun loadData(data: ArrayList<String>) {
            mineDesignData = data
        }

        private fun Context.getOutputDirectory(): File {
            val mediaDir = this.externalMediaDirs.firstOrNull()?.let {
                File(it, "ImagesFile").apply { mkdirs() }
            }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else this.filesDir
        }

        fun Context.getPhotosDirectory(): File {
            val mediaDir = this.externalMediaDirs.firstOrNull()?.let {
                File(it, this.resources.getString(R.string.app_name)).apply { mkdirs() }
            }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else this.filesDir
        }

        @SuppressLint("WrongConstant")
        private fun readsPost(context: Context, path: String, isDelete: Boolean) {
            if (isDelete) {
                val fileDelete = File(context.getOutputDirectory(), "Files.txt")
                val text = fileDelete.readText()
                val result: List<String> = text.split(",").map { it.trim() }
                if (fileDelete.exists()) {
                    if (fileDelete.delete()) {
                        Toast.makeText(context,"billedet er blevet slettet",0).show()
                    } else {
                        Toast.makeText(context,"billedet blev ikke slettet",0).show()
                    }
                }
                Log.d("Original Value", result.toString())
                var outputString = ""
                for (i in result.indices) {
                    when {
                        path != result[i] && i == 0 -> {
                            outputString += result[i]
                        }
                        path != result[i] && i > 0 -> {
                            outputString += ",${result[i]}"
                        }
                    }
                }
                if (outputString.isNotEmpty()) {
                    val file = File(context.getOutputDirectory(), "Files.txt")
                    Log.d("Update Value", outputString)
                    file.writeText(outputString)
                    UploadImage.deleteImage(path)
                }
            } else {
                val file = File(context.getOutputDirectory(), "Files.txt")
                file.readText()
            }
        }
    }

    @Composable
    fun FavoritButton(
        color: Color = Color.Red,
    ) {
        var isFavorite by remember { mutableStateOf(false) }
        IconToggleButton(checked = isFavorite, onCheckedChange = { isFavorite = !isFavorite }) {
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

    @ExperimentalComposeUiApi
    @Composable
    fun EditTitle() {
        val keyboardController = LocalSoftwareKeyboardController.current
        var textFieldState by remember {
            mutableStateOf("Title")
        }
        BasicTextField(
            textStyle = androidx.compose.ui.text.TextStyle(
                color = Color.Black,
                fontSize = 18.sp
            ),
            value = textFieldState,
            onValueChange = {
                textFieldState = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
        )
    }
}