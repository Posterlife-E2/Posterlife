package com.example.posterlife.view.inspirationView

import android.Manifest
import android.app.Activity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
//import com.example.posterlife.JsonParser.PlakatInfo
//import com.example.posterlife.Model.Plakat
import com.example.posterlife.model.jsonParser.PlakatInfo
import com.example.posterlife.model.Plakat
//import com.google.gson.Gson
import java.io.File
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

        private val inspirationViewModel = InspirationViewModel()

        @ExperimentalFoundationApi
        @Composable
        fun InspirationOverview(
            navController: NavController,
        ) {
            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    InspirationTopBar()
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


        @ExperimentalFoundationApi
        @Composable
        fun InspirationContent(
            navController: NavController
        ) {

            val context = LocalContext.current

            val plakatInfo = PlakatInfo(context)
            val plakatHolder: ArrayList<Plakat> = plakatInfo.getPlakatInfo()
            inspirationViewModel.getFavorites(context)

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
                        if (index in 20..30) {

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
                                                inspirationViewModel.setIndex(index)
                                                navController.navigate(InspirationFocusImage.rute) {
                                                    navController.popBackStack()
                                                }
                                            },
                                        contentScale = ContentScale.Crop
                                    )
                                }
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
                                    inspirationViewModel.setIndex(index)
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


    @ExperimentalFoundationApi
    @Composable
    fun InspirationTopBar() {

        TopAppBar(
            title = {

                Text(
                    text = "Inspiration",
                    color = Color.Black,
                    fontSize = 30.sp
                )
            },
            actions = {

                IconButton(onClick = { }) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
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


    object InspirationFocusImage : Inspiration("focusImage") {

        @Composable
        fun InspirationFocusImage() {
            Scaffold(
                topBar = {
                    FocusImageTopBar()
                },
                content = {
                    FocusImageContent()
                },
            )
        }

        @Composable
        fun FocusImageTopBar () {
            TopAppBar(
                title = {

                    Text(
                        text = "Inspiration",
                        color = Color.Black,
                        fontSize = 30.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            tint = Color.Black,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
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

        @Composable
        fun FocusImageContent () {
            val inspirationViewModel = InspirationViewModel
            val context = LocalContext.current
            val plakatInfo = PlakatInfo(context)
            val index = inspirationViewModel.currentIndex
            val plakatHolder = index?.let { plakatInfo.getPlakatInfo()[it] }
            var enlargeBillede = remember { mutableStateOf(false) }
            var pris by remember {
                mutableStateOf(" DKK" + plakatHolder.priceA3.toString() + " - " + "DKK " + plakatHolder.price70x100.toString())
            }

            if (plakatHolder != null) {

                Column(
                    Modifier
                        .background(Color(0xfffcfcf0))
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        "Forfatter",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(10.dp)
                    )

                    Row(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {

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
                                pris,
                                fontSize = 18.sp
                            )

                            var selectedpris = MenuItems()

                            if (selectedpris == 1) {
                                pris = "DKK 179,00"
                            }
                            if (selectedpris == 2) {
                                pris = "DKK 249,00"
                            }
                            if (selectedpris == 3) {
                                pris = "DKK 389,00"
                            }
                            else {

                            }
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

        }

        @Composable
        fun MenuItems(): Int {
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
            return selectedIndex

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
                textStyle = androidx.compose.ui.text.TextStyle(
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
        val inspirationViewModel = InspirationViewModel()

        ActivityCompat.requestPermissions(
            context as Activity,
            permissions, 0
        )


        val indexFile = File("index.txt")

        var isFavorite by remember { mutableStateOf(false) }
        //if (inspirationViewModel.getFavorites(context)[index] == index) {
        //    isFavorite = true
        //}
        IconToggleButton(checked = isFavorite, onCheckedChange = {
            isFavorite = !isFavorite
            if (isFavorite) {
                inspirationViewModel.setFavorites(index, context)
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



