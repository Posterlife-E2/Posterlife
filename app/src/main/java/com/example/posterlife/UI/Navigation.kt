package com.example.posterlife.UI

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    object Kamera : Navigation("kamera", R.drawable.ic_kamera, "Kamera")
    object Profil: Navigation("profil", R.drawable.ic_profil, "Profil")
    object MineDesign : Navigation("mine design", R.drawable.ic_minedesign, "Mine Design")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Inspiration.InspirationStart.rute) {
        composable(route = Inspiration.InspirationStart.rute) {
            InspirationMainUI(navController = navController)
        }
        composable(route = Inspiration.InspirationOther.rute) {
            InspirationOtherUI(navController = navController)
        }
    }

}