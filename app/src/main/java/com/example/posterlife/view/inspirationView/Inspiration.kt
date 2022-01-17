package com.example.posterlife.view.inspirationView

import android.Manifest
import android.app.Activity
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.posterlife.model.jsonParser.PlakatInfo
import com.example.posterlife.model.Plakat
import com.example.posterlife.view.NavigationBundNav
import kotlin.collections.ArrayList

/**
 * @Author Kristoffer Pedersen (s205354), Thamara Linnea (s205337), Camilla Bøjden (s205360)
 *
 * @source https://developer.android.com/jetpack/compose/navigation
 *
 *
 * @Source https://developer.android.com/jetpack/compose/state#viewmodel-and-jetpack-compose
 * @Source https://proandroiddev.com/jetpack-compose-navigation-architecture-with-viewmodels-1de467f19e1c
 *
 * Grid - https://www.geeksforgeeks.org/lazy-composables-in-android-jetpack-compose-columns-rows-grids/
 * Icon sizing - https://www.py4u.net/discuss/666679
 *
 *
 * @Source https://www.youtube.com/watch?v=KPVoQjwmWX4
 */

sealed class Inspiration(val rute: String) : ViewModel() {


    object InspirationStart : Inspiration("start") {

        private val inspirationViewModel = InspirationViewModel

        @ExperimentalCoilApi
        @ExperimentalComposeUiApi
        @ExperimentalFoundationApi
        @Composable
        fun InspirationOverview(
            navController: NavController,
        ) {
            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    InspirationTopBar(navController)
                },

                content = {
                    InspirationContent(navController = navController)
                }
            )
        }


        /**
         *
         * https://betterprogramming.pub/build-android-dropdownmenu-in-jetpack-compose-25512fe08068
         */

        @Composable
        fun DropdownMenu(
            menuItems: List<String>,
            menuExpandedState: Boolean,
            selectedIndex: Int,
            updateMenuExpandStatus: () -> Unit,
            onDismissMenuView: () -> Unit,
            onMenuItemClick: (Int) -> Unit,
        ) {
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(25.dp)
                    .clickable(onClick = { updateMenuExpandStatus() })
                    .background(MaterialTheme.colors.primaryVariant)
                    .border(0.9.dp, color = MaterialTheme.colors.onPrimary)
            )
            {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = menuItems[selectedIndex],
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(3.dp)
                    )

                    Icon(
                        Icons.Filled.ArrowDropDown,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = null,
                        modifier = Modifier.padding(top = 3.dp, bottom = 3.dp)
                    )
                }
                DropdownMenu(
                    expanded = menuExpandedState, onDismissRequest = { onDismissMenuView() },
                    modifier = Modifier
                        .width(200.dp)
                        .background(color = MaterialTheme.colors.primaryVariant)
                        .border(0.5.dp, color = MaterialTheme.colors.onPrimary)
                )
                {
                    menuItems.forEachIndexed { index, title ->
                        DropdownMenuItem(onClick = {
                            if (index != 0) {
                                onMenuItemClick(index)
                            }
                        }) {
                            Text(
                                text = title,
                                fontSize = 15.sp,
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                    }


                }
            }

        }


        @ExperimentalCoilApi
        @ExperimentalFoundationApi
        @Composable
        fun InspirationContent(
            navController: NavController
        ) {

            val context = LocalContext.current

            val plakatInfo = PlakatInfo(context)
            val plakatHolder: ArrayList<Plakat> = plakatInfo.getPlakatInfo()

            Column(
                Modifier
                    .background(MaterialTheme.colors.primary)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = "Nyheder",
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .offset(y = (8).dp)
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxHeight(0.4f)
                        .padding(12.dp)
                ) {


                    items(plakatHolder.size) { index ->

                        Card(
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .height(350.dp)
                                .width(182.dp)
                                .padding(10.dp),
                            elevation = 5.dp
                        ) {
                            Box(Modifier.fillMaxSize()) {
                                Image(
                                    painter = rememberImagePainter(
                                        data = plakatHolder.get(index).imageURL,
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            inspirationViewModel.currentIndex = index
                                            navController.navigate(InspirationFocusImage.rute) {
                                                popUpTo(0)
                                            }
                                        },
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }

                }


                Text(
                    "Find din yndlings sang eller digt blandt vores smukke plakater!",
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.subtitle2,
                    //fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(5.dp)
                )

                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                ) {

                    items(plakatHolder.size) { index ->

                        Card(
                            modifier = Modifier
                                .height(280.dp)
                                .width(150.dp)
                                .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp)
                                .clickable {
                                    inspirationViewModel.currentIndex = index
                                    navController.navigate("focusImage")
                                },
                            shape = RoundedCornerShape(4.dp),
                            elevation = 5.dp
                        ) {
                            Box(Modifier.fillMaxSize()) {

                                Image(
                                    contentScale = ContentScale.Crop,
                                    painter = rememberImagePainter(
                                        data = plakatHolder.get(index).imageURL,
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(300.dp)
                                )
                                Box(
                                    modifier = Modifier
                                        .matchParentSize()
                                        .background(
                                            Brush.verticalGradient(
                                                colors = listOf(Color.Transparent, Color.White),
                                            )
                                        )
                                )
                                {}

                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    contentAlignment = Alignment.BottomCenter
                                ) {

                                    Row() {
                                        Column(modifier = Modifier.weight(5f)) {
                                            Text(
                                                fontSize = 12.sp,
                                                style = MaterialTheme.typography.subtitle2,
                                                text = plakatHolder[index].title
                                            )
                                        }
                                        FavoritButton(
                                            index = index,
                                            modifier = Modifier
                                                .weight(1F)
                                        )
                                    }

                                }
                            }

                        }


                    }
                }

            }
        }
    }


    /**
     * @Source https://www.youtube.com/watch?v=trVmP1rw0uw&t=310s
     */
    @ExperimentalComposeUiApi
    @ExperimentalFoundationApi
    @Composable
    fun InspirationTopBar(navController: NavController) {


        val keyboardController = LocalSoftwareKeyboardController.current

        var expanded by remember { mutableStateOf(false)}
        var sizeState by remember { mutableStateOf(0.dp)}
        val size by animateDpAsState(
            targetValue = sizeState,
            tween(
                durationMillis = 400,
                easing = LinearOutSlowInEasing
            )
        )

        val query = remember {mutableStateOf("")}

        TopAppBar(
            title = {

                Text(
                    text = "Inspiration",
                    color = Color.Black,
                    fontSize = 30.sp,
                    maxLines = 1
                )
            },
            actions = {

                if(expanded)
                    TextField(
                        modifier = Modifier
                            .size(size)
                            .padding(1.dp),

                        value = query.value,

                        onValueChange = { newValue -> query.value = newValue},

                        keyboardOptions = KeyboardOptions (
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(onSearch = {
                            //plakatInfo.searchPlakat(query.value)
                            keyboardController?.hide() }),

                        textStyle = TextStyle(
                            fontSize = 18.sp,
                        ),

                        maxLines = 1,

                        leadingIcon = {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = null,

                                )
                        },

                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color (0xfffcfcf0),
                            textColor = Color.Black,
                            focusedIndicatorColor = Color.Black,
                            cursorColor = Color.Black,
                            leadingIconColor = Color.Black

                        )

                    )
                IconButton(onClick = {

                    expanded = !expanded
                    query.value = ""
                    if (expanded)
                        sizeState = 350.dp
                    else if (!expanded)
                        sizeState = 0.dp

                }) {
                    if(!expanded)
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = null
                        )
                    else if (expanded)
                        Icon(
                            Icons.Filled.ArrowForward,
                            contentDescription = null
                        )
                }
                if(!expanded)
                    IconButton(onClick = {
                        navController.navigate(NavigationBundNav.Favorit.route) {
                            navController.popBackStack()
                        }
                    })  {

                        Icon(
                            Icons.Filled.Favorite,
                            tint = Color.Red,
                            contentDescription = null
                        )
                    }
                if(!expanded)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = null)
                    }
            },


            backgroundColor = Color(0xfffcfcf0),

            elevation = 12.dp
        )

    }


    object InspirationFocusImage : Inspiration("focusImage") {

        @ExperimentalCoilApi
        @Composable
        fun InspirationFocusImage() {

            val inspirationViewModel = InspirationViewModel

            val context = LocalContext.current
            val plakatInfo = PlakatInfo(context)
            val index = inspirationViewModel.currentIndex
            val plakatHolder = index.let { plakatInfo.getPlakatInfo()[it] }
            val enlargeBillede = remember { mutableStateOf(false) }

            Column(
                Modifier
                    .background(Color(0xfffcfcf0))
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "Forfatter",
                    fontWeight = FontWeight.Light,
                    fontSize = 30.sp,
                )

                Row(modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 20.dp)) {

                    Image(
                        painter = rememberImagePainter(data = plakatHolder.imageURL),
                        contentDescription = null,
                        modifier = Modifier
                            .height(300.dp)
                            .width(200.dp)
                            .clickable {
                                enlargeBillede.value = true
                            }
                    )


                    Column(modifier = Modifier.padding(7.dp)) {
                        Text(plakatHolder.title, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            "DKK " + plakatHolder.priceA3.toString() + " - " + "DKK " + plakatHolder.price70x100.toString(),
                            fontSize = 18.sp
                        )

                        MenuItems()
                        Spacer(modifier = Modifier.height(4.dp))

                        Row(
                            modifier = Modifier
                                .width(200.dp)
                        ) {

                            PosterAmount()

                            Box(
                                modifier = Modifier
                                    .background(Color.Gray)
                                    .border(0.5.dp, Color.Black)
                                    .width(160.dp)
                                    .height(30.dp)
                                    .clickable { })
                            {
                                Text(
                                    "TILFØJ TIL KURV",
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    modifier = Modifier.padding(5.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(47.dp))
                        FavoritButton(modifier = Modifier.size(20.dp), index = index)
                    }
                }
                Text(
                    plakatHolder.description,
                    Modifier.padding(12.dp),
                    fontSize = 17.sp,
                    textAlign = TextAlign.Justify
                )

                if (enlargeBillede.value) {
                    AlertDialog(modifier = Modifier
                        .height(400.dp),
                        backgroundColor = Color.Transparent,
                        onDismissRequest = { enlargeBillede.value = false },
                        text = {
                            Image(
                                painter = rememberImagePainter(data = plakatHolder.imageURL),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        },
                        confirmButton = {})
                }

            }
        }

        @Composable
        fun MenuItems() {
            val options = listOf(
                "Vælg en mulighed",
                "A3 - 170g silk",
                "50x70 cm - 170g silk",
                "70x100 cm - 170g silk"
            )
            var optionsExpanded by remember { mutableStateOf(false) }
            var selectedIndex by remember { mutableStateOf(0) }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
            {
                InspirationStart.DropdownMenu(
                    menuItems = options,
                    menuExpandedState = optionsExpanded,
                    selectedIndex = selectedIndex,
                    updateMenuExpandStatus = { optionsExpanded = true },
                    onDismissMenuView = { optionsExpanded = false },
                    onMenuItemClick = { index ->
                        selectedIndex = index
                        optionsExpanded = false
                    }
                )
            }
        }

        @Composable
        fun PosterAmount() {
            var textFieldState by remember {
                mutableStateOf("1")
            }

            BasicTextField(
                modifier = Modifier
                    .height(30.dp)
                    .width(40.dp)
                    .background(color = Color.LightGray)
                    .border(0.5.dp, color = Color.Black)
                    .padding(5.dp),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                ),
                value = textFieldState,
                onValueChange = {
                    textFieldState = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

        }

    }

    /**
     * Funktion for FavoritButton, der gør det muligt at trykke på ikonet.
     * https://stackoverflow.com/questions/69453277/how-to-create-an-icon-in-the-corner-of-the-android-compose-card
     */

    private val permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @Composable
    fun FavoritButton(
        modifier: Modifier = Modifier,
        color: Color = Color.Red,
        index: Int
    ) {

        val context = LocalContext.current

        ActivityCompat.requestPermissions(
            context as Activity,
            permissions, 0
        )

//        val indexFile = File("index.txt")

        var isFavorite by remember { mutableStateOf(false) }
        IconToggleButton(checked = isFavorite, onCheckedChange = {
            isFavorite = !isFavorite
            if (isFavorite) {
//                indexFile.bufferedWriter().use { indexFil ->
//                    indexFil.write(index)
//                    indexFil.write("\n")
//                }
            }
        }) {
            Icon(
                tint = color, modifier = Modifier.size(25.dp), imageVector = if (isFavorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = null
            )
        }
    }

}



