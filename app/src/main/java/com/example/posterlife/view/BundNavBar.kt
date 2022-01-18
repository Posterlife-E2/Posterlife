package com.example.posterlife.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.posterlife.R

/**
 * @source https://johncodeos.com/how-to-create-bottom-navigation-bar-with-jetpack-compose/
 */

//Håndtering af bottom nav
sealed class NavigationBundNav(var route: String, var icon: Int, var title: String) {

    object Inspiration : NavigationBundNav("inspiration", R.drawable.ic_lightbulb, "Inspiration")
    object Favorit: NavigationBundNav("favorit",R.drawable.ic_favorite, "Favorit")
    object Kamera : NavigationBundNav("kamera", R.drawable.ic_kamera, "Kamera")
    object Profil : NavigationBundNav("profil", R.drawable.ic_profil, "Profil")
    object MineDesign : NavigationBundNav("mine design", R.drawable.ic_star, "Designs")
}

@Composable
fun BottomNavigationBar(navController: NavController) {


    val items = listOf(
        NavigationBundNav.Inspiration,
        NavigationBundNav.Favorit,
        NavigationBundNav.Kamera,
        NavigationBundNav.MineDesign,
        NavigationBundNav.Profil
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.secondaryVariant,
        contentColor = MaterialTheme.colors.onSecondary,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon,), contentDescription = item.title, modifier = Modifier
                    .size(30.dp)
                    .padding(top = 3.dp) ) },
                label = { Text(text = item.title, style = MaterialTheme.typography.overline)},
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.primary,
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
                        popUpTo(item.route)

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