package com.example.posterlife.view.profilUI

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.posterlife.R

/**
 * @Author Camilla Bøjden, (s205360)
 *
 * @Source https://posterlife.dk/handelsbetingelser/?fbclid=IwAR0djcebIY0rdN7v-8neduRGFUmgeTtyKfPW_nyt6w3g0fJF9SaB7IXJQR4
 */

sealed class Reklamationsret(val route: String) {
    object Reklamation : Reklamationsret("reklamation")

    @Composable
    fun ReklamationUI(navController: NavController) {


        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                ReklamationTopBar(navController = navController)

            },
            content = {
                DropdownInfo()


            }
        )
    }


    // Top app bar til Reklamationssiden. Den indeholder et ikon så det er muligt at gå tilbage til handelsbetingelser.
    @Composable
    fun ReklamationTopBar(navController: NavController) {
        TopAppBar(
            title = {

                Text(
                    text = "Handelsbetinger",
                    color = Color.Black,
                    fontSize = 30.sp
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate("betingelser") {
                        popUpTo("BetingelserOverview")
                    }
                }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        tint = Color.Black,
                        contentDescription = null
                    )
                }
            },
            backgroundColor = Color(0xfffcfcf0),

            elevation = 12.dp
        )

    }

    /**
     * Inspiration til dropdown: https://android--code.blogspot.com/2021/03/jetpack-compose-how-to-use-dropdownmenu.html
     * Denne funktion består af fire bokse som alle laver dropdown, med forskellig tekst ang. reklamation.
     * */

    @Composable
    fun DropdownInfo() {
        val context = LocalContext.current
        val expandedReklamationsret = remember { mutableStateOf(false) }
        val expandedRetur = remember { mutableStateOf(false) }
        val expandedOmbyt = remember { mutableStateOf(false) }
        val expandedRettigheder = remember { mutableStateOf(false) }


        Column(
            modifier = Modifier
                .background(Color(0xfffcfcf0))
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            // Overskrift
            Text(
                text = "Reklamation",
                fontSize = 30.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 5.dp)
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            Box(
                modifier = Modifier
                    .width(410.dp)
                    .height(60.dp)
                    .background(Color.White)
                    .border(5.dp, color = Color(0xfffcfcf0))
                    .padding(10.dp)
                    .align(CenterHorizontally)
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Text(
                        text = "Reklamationsret",
                        fontSize = 22.sp,
                        modifier = Modifier.padding()
                    )
                    Box(modifier = Modifier.weight(1f)) {

                    }
                    IconButton(onClick = { expandedReklamationsret.value = true }) {
                        Icon(Icons.Filled.ArrowDropDown, contentDescription = null)

                    }
                    // Dropdown som indeholder tekst ang. reklamations information.
                    DropdownMenu(
                        expanded = expandedReklamationsret.value,
                        onDismissRequest = { expandedReklamationsret.value = false },
                    ) {
                        Text(
                            text = context.getString(R.string.ReklamationsInfo),
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Light,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 10.dp)
                        )

                    }

                }
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Box(
                modifier = Modifier
                    .width(410.dp)
                    .height(60.dp)
                    .background(Color.White)
                    .border(1.dp, color = Color(0xfffcfcf0))
                    .padding(10.dp)
                    .align(CenterHorizontally)
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {

                    Text(
                        text = "Retur- og ombytningsret",
                        fontSize = 20.sp,
                        modifier = Modifier.padding()
                    )
                    Box(modifier = Modifier.weight(1f)) {

                    }
                    IconButton(onClick = { expandedRettigheder.value = true }) {
                        Icon(Icons.Filled.ArrowDropDown, contentDescription = null)

                    }
                    // Dropdown som indeholder tekst ang. reklamations -og ombytningsret.
                    DropdownMenu(
                        expanded = expandedRettigheder.value,
                        onDismissRequest = { expandedRettigheder.value = false },
                    ) {
                        Text(
                            text = context.getString(R.string.Retur_ombytningsret),
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Light,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                        )

                    }
                }
            }

            Spacer(modifier = Modifier.padding(top = 10.dp))

            Box(
                modifier = Modifier
                    .width(410.dp)
                    .height(60.dp)
                    .background(Color.White)
                    .border(1.dp, color = Color(0xfffcfcf0))
                    .padding(10.dp)
                    .align(CenterHorizontally)
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {

                    Text(
                        text = "Returret/ombytning",
                        fontSize = 20.sp,
                        modifier = Modifier.padding()
                    )
                    Box(modifier = Modifier.weight(1f)) {

                    }
                    IconButton(onClick = { expandedOmbyt.value = true }) {
                        Icon(Icons.Filled.ArrowDropDown, contentDescription = null)

                    }
                    // Dropdown som indeholder tekst ang. ombytning.
                    DropdownMenu(
                        expanded = expandedOmbyt.value,
                        onDismissRequest = { expandedOmbyt.value = false },
                    ) {
                        Text(
                            text = context.getString(R.string.Returret_ombytning),
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Light,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                        )

                    }
                }
            }

            Spacer(modifier = Modifier.padding(top = 10.dp))


            Box(
                modifier = Modifier
                    .width(410.dp)
                    .height(60.dp)
                    .background(Color.White)
                    .border(1.dp, color = Color(0xfffcfcf0))
                    .padding(10.dp)
                    .align(CenterHorizontally)
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {

                    Text(
                        text = "Reklamation, returnering & ombytning",
                        fontSize = 20.sp,
                        modifier = Modifier.padding()
                    )
                    Box(modifier = Modifier.weight(1f)) {

                    }
                    IconButton(onClick = { expandedRetur.value = true }) {
                        Icon(Icons.Filled.ArrowDropDown, contentDescription = null)

                    }
                    // Dropdown som indeholder tekst ang. retur.
                    DropdownMenu(
                        expanded = expandedRetur.value,
                        onDismissRequest = { expandedRetur.value = false },
                    ) {
                        Text(
                            text = context.getString(R.string.ReklmationRetur),
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Light,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                        )

                    }
                }
            }

        }

    }

}
