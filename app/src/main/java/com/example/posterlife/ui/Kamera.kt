package com.example.posterlife.ui

sealed class Kamera(val route: String) {

    object KameraAccess : Kamera("openKamera") {


    }
}