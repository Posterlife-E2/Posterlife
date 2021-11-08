package com.example.posterlife.UI

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * @Source https://www.youtube.com/watch?v=4gUeyNkGE3g
 */

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Inspiration.InspirationStart.rute) {
        composable(route = Inspiration.InspirationStart.rute) {
            InspirationMain(navController = navController)
        }
    }
}

@Composable
fun InspirationMain(navController: NavController) {
    Text("Hello World")
}
