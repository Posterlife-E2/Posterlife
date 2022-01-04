package com.example.posterlife.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.posterlife.ui.Inspiration.InspirationFocusImage.InspirationFocusImage
import com.example.posterlife.ui.Inspiration.InspirationStart.InspirationOverview
import com.example.posterlife.ui.loginUI.Login
import com.example.posterlife.ui.loginUI.SignUp

/**
 * @Source https://www.youtube.com/watch?v=4gUeyNkGE3g
 * @source https://johncodeos.com/how-to-create-bottom-navigation-bar-with-jetpack-compose/
 */


@Composable
fun Navigation() {

    val navController = rememberNavController()

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomNavigationBar(navController) },
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

                //--------------------

                //---- Kamera ----

                composable(Navigation.Kamera.route) {
                    Kamera.KameraAccess.KameraAccess(onImageCaptured = { uri, fromGallery ->
                        Log.d(TAG, "Image Uri Captured from Camera View")

                    }, onError = { imageCaptureException ->
                        navController.navigate("inspirationStart")
                    })
                }

                composable(Kamera.KameraAccess.route) {
                    navController.navigate(Navigation.Kamera.route)
                }

                //----------------

                //---- Profil ----
                composable(Navigation.Profil.route) {
                    Profil.ProfilUI.ProfilUI(navController = navController)
                }

                composable(Profil.ProfilUI.rute) {
                    Profil.ProfilUI.ProfilUI(navController = navController)
                }
                //----------------

                //---- Mine Design ----

                composable(Navigation.MineDesign.route) {}
                //---------------------

                //---- Login & Sign Up ----
                composable(Login.LoginScreen.route) {
                    Login.LoginScreen.LoginStart(navController = navController)
                }
                composable(SignUp.SignUpScreen.route) {
                    SignUp.SignUpScreen.SignUpScreen(navController = navController)
                }

                //------------------------

            }
        }
    )
}

