package com.example.posterlife.UI

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.posterlife.UI.Inspiration.InspirationOther.InspirationOtherUI
import com.example.posterlife.UI.Inspiration.InspirationStart.InspirationMainUI

/**
 * @Source https://www.youtube.com/watch?v=4gUeyNkGE3g
 */

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