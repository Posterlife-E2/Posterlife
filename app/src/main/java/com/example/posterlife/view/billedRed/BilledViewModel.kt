package com.example.posterlife.view.billedRed

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BilledViewModel : ViewModel() {

    var billedURI = mutableStateOf("")

    fun setBilledURI(uri: Uri) {
        billedURI.value = uri.toString()
    }

    fun getBilledURI(): Uri {
        return Uri.parse(billedURI.value)
    }
}