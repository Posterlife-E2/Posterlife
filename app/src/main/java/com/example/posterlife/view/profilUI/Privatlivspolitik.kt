package com.example.posterlife.view.profilUI

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.ui.core.Text
import com.example.posterlife.R
import androidx.compose.material.Text as MaterialText

sealed class Privatlivspolitik(var route: String) {
    object Privatpolitik : Privatlivspolitik("privatPolitik")


    @Composable
    fun PrivatPolitikOverview(navController: NavController) {


        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                PrivatPolitikTopBar(navController = navController)

            },
            content = {
                Priv()
            }
        )
    }

    // top app bar til leveringssiden. den indeholder et ikon så det er muligt at gå tilbage.
    @Composable
    fun PrivatPolitikTopBar(navController: NavController) {
        TopAppBar(
            title = {

                MaterialText(
                    text = "Privatlivspolitik",
                    color = Color.Black,
                    fontSize = 30.sp
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate("profilStart") {
                        popUpTo("profilUI")
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

    @Composable
    fun Priv() {
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .background(Color(0xfffcfcf0))
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())

        ) {

            // Overskrift
            androidx.compose.material.Text(
                text = "Personoplysninger",
                fontSize = 30.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp)
            )
            // Overskrift til generalt
            androidx.compose.material.Text(
                text = "Generalt:",
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 25.dp, start = 10.dp, end = 10.dp)

            )
            // Tekst der beskriver generalt.
            androidx.compose.material.Text(
                text = context.getString(R.string.PersonoplysningerGeneralt),
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp)

            )
            // Overskrift til indsamling og behandling af oplysninger
            androidx.compose.material.Text(
                text = "Vi indsamler og behandler typisk følgende typer af oplysninger:",
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 25.dp, start = 10.dp, end = 10.dp)

            )
            // teksten der beskriver "indsamling og behandling af oplysninger"
            androidx.compose.material.Text(
                text = context.getString(R.string.PersonoplysningerIndsamler),
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp)

            )
            // overskrift til sikkerhed
            androidx.compose.material.Text(
                text = "Sikkerhed:",
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 25.dp, start = 10.dp, end = 10.dp)

            )
            // teksten der beskriver sikkerhed.
            androidx.compose.material.Text(
                text = context.getString(R.string.PersonoplysningerSikkerhed),
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp)

            )
            // Overskrift formål
            androidx.compose.material.Text(
                text = "Formål:",
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 25.dp, start = 10.dp, end = 10.dp)

            )
            // Teksten der beskriver formål
            androidx.compose.material.Text(
                text = context.getString(R.string.PersonoplysningerFormål),
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp)

            )

            // Overskrift for periode og opbevaring
            androidx.compose.material.Text(
                text = "Periode for opbevaring:",
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 25.dp, start = 10.dp, end = 10.dp)

            )
            // Teksten der beskriver Periode for opbevaring.
            androidx.compose.material.Text(
                text = context.getString(R.string.PersonoplysningerFormål),
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp)

            )

            // Overskrift for Videregivelse af oplysninger
            androidx.compose.material.Text(
                text = "Videregivelse af oplysninger:",
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 25.dp, start = 10.dp, end = 10.dp)

            )
            // Teksten der beskriver Videregivelse af oplysninger
            androidx.compose.material.Text(
                text = context.getString(R.string.VideregivelseAfOplysninger),
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp)

            )

            // Overskrift for Indsigt og klager
            androidx.compose.material.Text(
                text = "Indsigt og klager:",
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 25.dp, start = 10.dp, end = 10.dp)

            )
            // Teksten der beskriver Indsigt og klager
            androidx.compose.material.Text(
                text = context.getString(R.string.IndsigtOgKlager),
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp)

            )
            // Tekst med mail til indsigt og klager
            androidx.compose.material.Text(
                text = context.getString(R.string.mailKlage),
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp)

            )
            // Tekst klage
            androidx.compose.material.Text(
                text = context.getString(R.string.Klage),
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 80.dp)

            )

        }

    }
}








