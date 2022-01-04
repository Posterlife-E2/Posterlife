package com.example.posterlife.ui

sealed class BilledRedigering(var rute: String) {

    object BilledConfirm : BilledRedigering("billedConfirm") {


    }

    object BilledRed : BilledRedigering("billedRed") {

    }

}