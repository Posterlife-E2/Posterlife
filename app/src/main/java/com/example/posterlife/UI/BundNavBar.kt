package com.example.posterlife.UI

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

/**
 * @source https://johncodeos.com/how-to-create-bottom-navigation-bar-with-jetpack-compose/
 */

@Composable
fun BundNavBar() {
    Scaffold(
        bottomBar = {BottomNavigationBar()}
    ) {

    }
}

@Composable
fun BottomNavigationBar() {
    val items = listOf(
        Navigation.Hjem,
        Navigation.Inspiration,
        Navigation.Kamera,
        Navigation.Profil,
        Navigation.MineDesign
    )
    BottomNavigation(
        backgroundColor = Color.Blue,
        contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    /* Add code later */
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}