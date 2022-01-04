package com.example.posterlife.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.posterlife.R

/**
 * @source https://johncodeos.com/how-to-create-bottom-navigation-bar-with-jetpack-compose/
 */

//Håndtering af bottom nav
sealed class Navigation(var route: String, var icon: Int, var title: String) {
    object Inspiration : Navigation("inspiration", R.drawable.ic_lightbulb, "Inspiration")
    object Kamera : Navigation("kamera", R.drawable.ic_kamera, "Kamera")
    object Profil : Navigation("profil", R.drawable.ic_profil, "Profil")
    object MineDesign : Navigation("mine design", R.drawable.ic_minedesign, "Mine Design")
}

@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf(
        Navigation.Inspiration,
        Navigation.Kamera,
        Navigation.Profil,
        Navigation.MineDesign
    )
    BottomNavigation(
        backgroundColor = Color(0xFF999999),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color(0xfffcfcf0),
                unselectedContentColor = Color(0xfffcfcf0).copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {


                        //Undgår ophopning af destinations.
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }

                        //Så vi undgår at den åbner den samme ting flere gange ved tryk.
                        launchSingleTop = true

                        //Husker hvad der skete på forrige frame
                        restoreState = true
                    }


                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
//    BottomNavigationBar()
}