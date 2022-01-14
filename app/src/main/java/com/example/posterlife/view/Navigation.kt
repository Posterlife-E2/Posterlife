package com.example.posterlife.view

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.posterlife.model.jsonParser.MineDesignInfo
import com.example.posterlife.view.profilUI.Reklamationsret
import com.example.posterlife.view.inspirationView.Inspiration.InspirationStart.InspirationOverview
import com.example.posterlife.view.inspirationView.Inspiration.InspirationFocusImage.InspirationFocusImage
import com.example.posterlife.view.Favorit.FavoritStart.FavoritOverview
import com.example.posterlife.view.billedRed.BilledRedigering
import com.example.posterlife.view.billedRed.BilledRedigering.BilledConfirm
import com.example.posterlife.view.billedRed.BilledViewModel
import com.example.posterlife.view.inspirationView.Inspiration
import com.example.posterlife.view.loginUI.Login
import com.example.posterlife.view.loginUI.SignUp
import com.example.posterlife.view.profilUI.*
/**
 * @Source https://www.youtube.com/watch?v=4gUeyNkGE3g
 * @source https://johncodeos.com/how-to-create-bottom-navigation-bar-with-jetpack-compose/
 */


@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalCoilApi
@Composable
fun Navigation() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    MineDesignInfo.getMineDesignInfo()

    val billedViewModel = BilledViewModel()

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            if ((navBackStackEntry?.destination?.route != Navigation.Kamera.route) and
                (navBackStackEntry?.destination?.route != BilledRedigering.BilledRed.rute) and
                (navBackStackEntry?.destination?.route != BilledConfirm.rute))
                BottomNavigationBar(navController)
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = Login.LoginScreen.route
            ) {
                //---- Inspiration ----
                composable(Navigation.Inspiration.route) {
                    InspirationOverview(navController = navController)
                }

                composable(Inspiration.InspirationFocusImage.rute) {
                    InspirationFocusImage()
                }


                //----Favorit ----
                composable(Navigation.Favorit.route) {
                    FavoritOverview(navigation = navController)
                }

                //---- Kamera ----

                composable(Navigation.Kamera.route) {
                    Kamera.KameraAccess.KameraAccess(onImageCaptured = { uri, fromGallery ->
                        Log.d(TAG, "Image Uri Captured from Camera View")

                    }, onError = { imageCaptureException ->
                        navController.navigate(Navigation.Inspiration.route)
                    },
                        navController = navController,
                        billedViewModel
                    )
                }

                composable(Kamera.KameraAccess.route) {
                    navController.navigate(Navigation.Kamera.route)
                }

                //------ Del med venner ----------
                composable(DelMedVenner.DelStart.route) {
                    DelMedVenner.DelStart.DelOverview(navController = navController)
                }

                //---- Profil ----
                composable(Navigation.Profil.route) {
                    Profil.ProfilUI.ProfilUI(navController = navController)
                }

                composable(Profil.ProfilUI.rute) {
                    Profil.ProfilUI.ProfilUI(navController = navController)
                }
                //----- Handelsbetingelser ------
                composable(Handelsbetingelser.Betingelser.route){
                    Handelsbetingelser.Betingelser.BetingelserOverview(navController = navController)

                }

                //---- Betalingsinfo ----
                composable(BetalingsInfo.InfoBetaling.route){
                    BetalingsInfo.InfoBetaling.BetalingOverview(navController = navController)
                }

                //---- Levering ----
                composable(Levering.LeveringUI.route){
                    Levering.LeveringUI.LeveringOverview(navController = navController)
                }

                //---- Kontakt ----
                composable(Kontakt.KontaktInfo.route){
                    Kontakt.KontaktInfo.KontaktOverview(navController = navController)
                }

                //---- Reklamationsret ----
                composable(Reklamationsret.Reklamation.route){
                    Reklamationsret.Reklamation.ReklamationUI(navController = navController)
                }

                //---- Mine Design ----
                composable(Navigation.MineDesign.route) {
                    MineDesign.MineDesignStart.MineDesignStart()
                }

                //---- Login & Sign Up ----
                composable(Login.LoginScreen.route) {
                    Login.LoginScreen.LoginStart(navController = navController)
                }
                composable(SignUp.SignUpScreen.route) {
                    SignUp.SignUpScreen.SignUpScreen(navController = navController)
                }
                //------------------------

                //---- Redigering -----
                composable(BilledRedigering.BilledRed.rute,) {
                    BilledRedigering.BilledRed.BilledRedigering(
                        billedViewModel,
                        navController = navController
                    )
                }

                composable(BilledConfirm.rute)
                {
                    BilledConfirm.BilledConfirm(billedViewModel, navController = navController)
                }

            }
        }
    )
}


