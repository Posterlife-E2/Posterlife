package com.example.posterlife.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.posterlife.ui.Inspiration.InspirationStart.InspirationOverview
import com.example.posterlife.ui.Inspiration.InspirationFocusImage.InspirationFocusImage
import com.example.posterlife.ui.Favorit.FavoritStart.FavoritOverview
import com.example.posterlife.ui.profilUI.DelMedVenner.DelStart.DelOverview
import com.example.posterlife.ui.profilUI.Handelsbetingelser.Betingelser.BetingelserOverview
import com.example.posterlife.ui.profilUI.Levering.LeveringUI.LeveringOverview
import com.example.posterlife.ui.profilUI.BetalingsInfo.InfoBetaling.BetalingOverview
import com.example.posterlife.ui.profilUI.Reklamationsret.Reklamation.ReklamationUI
import com.example.posterlife.ui.profilUI.Kontakt.KontaktInfo.KontaktOverview
import com.example.posterlife.ui.billedRed.BilledRedigering
import com.example.posterlife.ui.billedRed.BilledRedigering.BilledConfirm
import com.example.posterlife.ui.loginUI.Login
import com.example.posterlife.ui.loginUI.SignUp
import com.example.posterlife.ui.profilUI.*

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

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            if (navBackStackEntry?.destination?.route != Navigation.Kamera.route)
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

                composable(
                    Inspiration.InspirationFocusImage.rute,
                    arguments = listOf(navArgument("plakatIndex") { type = NavType.IntType })

                ) { backStackEntry ->
                    InspirationFocusImage(backStackEntry.arguments?.getInt("plakatIndex"))
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
                        navController = navController
                    )
                }

                composable(Kamera.KameraAccess.route) {
                    navController.navigate(Navigation.Kamera.route)
                }

                //------ Del med venner ----------
                composable(DelMedVenner.DelStart.route) {
                    DelOverview(navController = navController)
                }

                //---- Profil ----
                composable(Navigation.Profil.route) {
                    Profil.ProfilUI.ProfilUI(navController = navController)
                }

                composable(Profil.ProfilUI.rute) {
                    Profil.ProfilUI.ProfilUI(navController = navController)
                }
                //---- Handelsbetingelser ----
                composable(Handelsbetingelser.Betingelser.route){
                    BetingelserOverview(navController = navController)
                    
                }
                
                //---- Levering ----
                composable(Levering.LeveringUI.route){
                    LeveringOverview(navController = navController)
                }

                //---- BetalingsInfo ----
                composable(BetalingsInfo.InfoBetaling.route){
                    BetalingOverview(navController = navController)
                }
                
                //---- Reklamation ----
                composable(Reklamationsret.Reklamation.route){
                    ReklamationUI(navController = navController)
                    
                }
                
                //---- Kontakt ------
                composable(Kontakt.KontaktInfo.route){
                    KontaktOverview(navController = navController)
                    
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
                composable(
                    BilledRedigering.BilledRed.rute,
                    arguments = listOf(navArgument("billedURI") { type = NavType.StringType })
                ) { backStackEntry ->
                    BilledRedigering.BilledRed.BilledRedigering(
                        backStackEntry.arguments?.getString("billedURI"),
                        navController = navController
                    )
                }

                composable(
                    BilledRedigering.BilledConfirm.rute,
                    arguments = listOf(navArgument("billedURI") { type = NavType.StringType })
                ) { backStackEntry ->
                    BilledConfirm.BilledConfirm(
                        backStackEntry.arguments?.getString("billedURI"),
                        navController = navController
                    )
                }

            }
        }
    )
}


