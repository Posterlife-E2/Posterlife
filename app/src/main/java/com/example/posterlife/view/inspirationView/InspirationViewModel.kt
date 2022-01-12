package com.example.posterlife.view.inspirationView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * @Author Kristoffer Pedersen (s205354)
 *
 * @Source https://developer.android.com/jetpack/compose/state#viewmodel-and-jetpack-compose
 * @Source https://proandroiddev.com/jetpack-compose-navigation-architecture-with-viewmodels-1de467f19e1c
 */

object InspirationViewModel : ViewModel() {
   var currentIndex by mutableStateOf(0)
}