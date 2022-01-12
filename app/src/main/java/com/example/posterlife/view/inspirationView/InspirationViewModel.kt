package com.example.posterlife.view.inspirationView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class InspirationViewModel : ViewModel() {
   var currentIndex by mutableStateOf(0)
}