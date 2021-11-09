package com.example.posterlife.UI

sealed class Inspiration(val rute: String) {
    object InspirationStart : Inspiration("start")
}