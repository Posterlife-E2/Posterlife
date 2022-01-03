package com.example.posterlife.UI

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.posterlife.UI.Inspiration.InspirationFocusImage.InspirationFocusImage
import com.example.posterlife.UI.Inspiration.InspirationStart.InspirationOverview
import com.example.posterlife.UI.LoginUI.Login
import com.example.posterlife.UI.LoginUI.SignUp

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
                composable(Navigation.Inspiration.route) {
                    InspirationOverview(navController = navController)
                }

                composable(
                    Inspiration.InspirationFocusImage.rute,
                    arguments = listOf(navArgument("plakatIndex") { type = NavType.IntType })

                ) {
                    backStackEntry -> InspirationFocusImage(backStackEntry.arguments?.getInt("plakatIndex"))
                }

                composable(Navigation.Kamera.route) {

                }

                composable(Navigation.Profil.route) {
                    Profil.ProfilUI.ProfilUI(navController = navController)
                }

                composable(Navigation.MineDesign.route) {}

                composable(Login.LoginScreen.route) {
                    Login.LoginScreen.LoginStart(navController = navController)
                }
                composable(SignUp.SignUpScreen.route) {
                    SignUp.SignUpScreen.SignUpScreen(navController = navController)
                }

            }
        }
    )
}

