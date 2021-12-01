package com.example.posterlife.UI

sealed class Kamera(val route: String) {

    object KameraAccess: Kamera("start") {


    }
}