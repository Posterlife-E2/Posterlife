package com.example.posterlife.UI

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.posterlife.R
import com.example.posterlife.UI.Inspiration.InspirationOther.InspirationOtherUI
import com.example.posterlife.UI.Inspiration.InspirationStart.InspirationMainUI

/**
 * @Source https://www.youtube.com/watch?v=4gUeyNkGE3g
 * @source https://johncodeos.com/how-to-create-bottom-navigation-bar-with-jetpack-compose/
 */

sealed class Navigation(var route: String, var icon: Int, var title: String) {
    object Hjem : Navigation("hjem", R.drawable.ic_hjem, "Hjem")
    object Inspiration : Navigation("inspiration", R.drawable.ic_lightbulb, "Inspiration")
    object Camera : Navigation("kamera", R.drawable.ic_kamera, "Kamera")
    object Profil: Navigation("profil", R.drawable.ic_profil, "Profil")
    object MineDesign : Navigation("mine design", R.drawable.ic_minedesign, "Mine Design")
}

@Composable
fun Navigation(navController: NavController) {

    NavHost(navController = navController as NavHostController, startDestination = Navigation.Inspiration.route) {
        composable(Navigation.Hjem.route) {
            Hjem.HjemStart.HjemStartUI(navController = navController)
        }
        composable(route = Navigation.Inspiration.route) {
            InspirationMainUI(navController = navController)
        }
        composable(route = Inspiration.InspirationOther.rute) {
            InspirationOtherUI(navController = navController)
        }
        composable(Navigation.Camera.route) {


        }
        composable(Navigation.Profil.route) {
            Profil.ProfilUI.ProfilUI(navController = navController)
        }
        composable(Navigation.MineDesign.route) {}

    }

}