package com.example.posterlife.UI

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.posterlife.R
import com.example.posterlife.UI.Inspiration.InspirationOther.InspirationOtherUI
import com.example.posterlife.UI.Inspiration.InspirationStart.InspirationMainUI
import com.example.posterlife.UI.LoginUI.Login
import com.example.posterlife.UI.LoginUI.SignUp

/**
 * @Source https://www.youtube.com/watch?v=4gUeyNkGE3g
 * @source https://johncodeos.com/how-to-create-bottom-navigation-bar-with-jetpack-compose/
 */

sealed class Navigation(var route: String, var icon: Int, var title: String) {
    object Hjem : Navigation("hjem", R.drawable.ic_hjem, "Hjem")
    object Inspiration : Navigation("inspiration", R.drawable.ic_lightbulb, "Inspiration")
    object Kamera : Navigation("kamera", R.drawable.ic_kamera, "Kamera")
    object Profil : Navigation("profil", R.drawable.ic_profil, "Profil")
    object MineDesign : Navigation("mine design", R.drawable.ic_minedesign, "Mine Design")
}

@Composable
fun Navigation() {

    val navController = rememberNavController()

    BundNavBar(navController)

    NavHost(
        navController = navController as NavHostController,
        startDestination = Login.LoginScreen.route
    ) {
        composable(Navigation.Hjem.route) {
            Scaffold(
                content = {
                    Hjem.HjemStart.HjemStartUI(navController = navController)
                },
                bottomBar = { BottomNavigationBar(navController = navController) }
            )
        }
        composable(Navigation.Inspiration.route) {
            Scaffold(
                content = {
                    InspirationMainUI(navController = navController)
                },
                bottomBar = { BottomNavigationBar(navController = navController) }
            )
        }
        composable(Inspiration.InspirationOther.rute) {
            // InspirationOtherUI(navController = navController)
            ScaffoldControl(
                contentUnit = InspirationOtherUI(navController = navController),
                navController = navController
            )
        }
        composable(Navigation.Kamera.route) {}
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

@Composable
fun ScaffoldControl(contentUnit: Unit, navController: NavController) {
    Scaffold(
        content = {
            contentUnit
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    )
}

