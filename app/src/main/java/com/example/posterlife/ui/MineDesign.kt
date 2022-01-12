package com.example.posterlife.ui

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.posterlife.R
import com.example.posterlife.saveImageController.UploadImage
import com.firebase.ui.auth.ui.phone.CountryListSpinner
import java.io.File
import androidx.compose.foundation.text.BasicTextField as BasicTextField


/**
 * @source https://developer.android.com/jetpack/compose/navigation
 *
 * Ting til at lave ting.
 * https://juliensalvi.medium.com/parallax-effect-made-it-simple-with-jetpack-compose-d19bde5688fc
 * https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example
 */


sealed class MineDesign(val rute: String) {

    object MineDesignStart : MineDesign("MineDesignStart") {
        var mineDesignData = ArrayList<String>()

        @ExperimentalFoundationApi
        @ExperimentalCoilApi
        @androidx.compose.runtime.Composable
        fun MineDesignOverview(navController: NavController) {

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


                    IconButton(onClick = {
                        navController.navigate("Favorit") {
                            popUpTo("FavoritOverview")
                        }
                    }) {
                        Icon(
                            Icons.Filled.Favorite,
                            tint = Color.Red,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = null)
                    }
                },


                backgroundColor = Color(0xfffcfcf0),

                elevation = 12.dp
            )

        }

        @ExperimentalFoundationApi
        @Composable
        fun MineDesignContent() {
            val context = LocalContext.current;
            var file = File(context.getOutputDirectory(), "Files.txt")

            if (file.exists()) {
                var file = File(context.getOutputDirectory(), "Files.txt")
                val text = file.readText()
                var result: List<String> = text.split(",").map { it.trim() }
                Log.d("Data", result.toString())
                if (result.isNotEmpty()) {
                    val openDialog = remember { mutableStateOf(false) }
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp),
                        modifier = Modifier
                            .background(Color(0xfffcfcf0))
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        items(result.size) { index ->
                            val source = BitmapFactory.decodeFile(
                                context.getPhotosDirectory().absolutePath + "/" + result.get(
                                    index
                                )
                            )
                            Column(
                                modifier = Modifier
                                    .padding(
                                        start = 12.dp,
                                        top = 5.dp,
                                        end = 12.dp,
                                        bottom = 15.dp
                                    )
                                    .background(Color.White)
                            ) {

                                Row {
                                    EditTitle()
                                    Box(modifier = Modifier.weight(1f))
                                    Box() {

                                        val showDialog =
                                            IconButton(
                                                onClick = { openDialog.value = true },
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
                                        data = source
                                    ),
                                    contentDescription = "Image",
                                    modifier = Modifier
                                        .size(250.dp)
                                )

                                Row {

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
                                        Text(text = "Slet plakat")
                                    },
                                    text = {
                                        Text("Er du sikker på at du vil slette denne plakat")
                                    },
                                    confirmButton = {
                                        Button(
                                            onClick = {
                                                openDialog.value = false
                                                ReadsPost(context, result.get(index), true)

                                            }) {
                                            Text("Ok")
                                        }
                                    },
                                    dismissButton = {
                                        Button(
                                            onClick = {
                                                openDialog.value = false

                                            }
                                        ) {
                                            Text("Cancel")
                                        }
                                    }
                                )
                            }
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
                            text = "Mine Design er tom",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }

        }

        @Composable
        fun EditTitle() {
            var textFieldState by remember {
                mutableStateOf("Title")
            }
            BasicTextField(
                textStyle = androidx.compose.ui.text.TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp
                ),
                value = textFieldState,
                onValueChange = {
                    textFieldState = it
                },
            )
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

        @Composable
        fun MineDesignNajib() {
            val context = LocalContext.current;
            var file = File(context.getOutputDirectory(), "Files.txt")

            if (file.exists()) {
                var file = File(context.getOutputDirectory(), "Files.txt")
                val text = file.readText()
                var result: List<String> = text.split(",").map { it.trim() }
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
                        Text(
                            text = "Mine Design",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        LazyColumn(
                            Modifier
                                .background(Color(0xfffcfcf0))
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(result.size) { index ->
                                val source = BitmapFactory.decodeFile(
                                    context.getPhotosDirectory().absolutePath + "/" + result.get(
                                        index
                                    )
                                )
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clickable { },
                                    elevation = 10.dp

                                ) {
                                    Column(

                                    ) {
                                        Image(
                                            painter = rememberImagePainter(
                                                data = source
                                            ),
                                            contentDescription = "Image",
                                            modifier = Modifier
                                                .size(500.dp)
                                                .background(Color.Black)
                                                .clickable {

                                                }
                                        )
                                        Button(
                                            onClick = {
                                                openDialog.value = true
                                            },
                                            modifier = Modifier.fillMaxWidth(),
                                            contentPadding = PaddingValues(
                                                start = 20.dp,
                                                top = 12.dp,
                                                end = 20.dp,
                                                bottom = 12.dp
                                            )

                                        ) {
                                            // Inner content including an icon and a text label
                                            Icon(
                                                Icons.Filled.Delete,
                                                contentDescription = "Delete",
                                                modifier = Modifier.size(ButtonDefaults.IconSize)
                                            )
                                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                            Text("Delete")
                                        }
                                    }
                                    if (openDialog.value) {
                                        AlertDialog(
                                            onDismissRequest = {
                                                openDialog.value = false
                                            },
                                            title = {
                                                Text(text = "Delete Post")
                                            },
                                            text = {
                                                Text("Are you sure you want to delete the post?")
                                            },
                                            confirmButton = {
                                                Button(
                                                    onClick = {
                                                        openDialog.value = false
                                                        ReadsPost(context, result.get(index), true)
                                                    }) {
                                                    Text("Ok")
                                                }
                                            },
                                            dismissButton = {
                                                Button(
                                                    onClick = {
                                                        openDialog.value = false

                                                    }
                                                ) {
                                                    Text("Cancel")
                                                }
                                            }
                                        )
                                    }
                                }

                            }
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
                            text = "Mine Design er tom",
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
                        text = "Mine Design er tom",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                }
            }

        }


        fun loadData(data: ArrayList<String>) {
            mineDesignData = data
        }


        fun android.content.Context.getOutputDirectory(): File {
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

        private fun ReadsPost(context: Context, path: String, isDelete: Boolean) {
            if (isDelete) {
                var fdelete = File(context.getOutputDirectory(), "Files.txt")
                val text = fdelete.readText()
                var result: List<String> = text.split(",").map { it.trim() }
                if (fdelete.exists()) {
                    if (fdelete.delete()) {
                        System.out.println("file Deleted")
                    } else {
                        System.out.println("file not Deleted")
                    }
                }
                Log.d("Original Value", result.toString());
                var outputString: String = ""
                for (i in 0..result.size - 1) {
                    if (!path.equals(result.get(i)) && i === 0) {
                        outputString += result.get(i);
                    } else if (!path.equals(result.get(i)) && i > 0) {
                        outputString += ",${result.get(i)}";
                    }
                }
                if (outputString.length > 0) {
                    var file = File(context.getOutputDirectory(), "Files.txt")
                    Log.d("Update Value", outputString);
                    file.writeText(outputString);
                    UploadImage.DeleteImage(path)
                }

            } else {
                var file = File(context.getOutputDirectory(), "Files.txt")
                val text = file.readText()
            }
        }

    }
}