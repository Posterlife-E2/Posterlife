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
 *
 * Det er med fuld belæg at dette er et objekt. Compose ser gerne at vi undgår at pass arguments mellem sider, så vidt muligt
 * Derfor giver det god mening at lave vores viewModel til et objekt, hvis selve data staten er irrelevant over flere screens.
 * Vi skal kun bruge en plakatIndex: Int kortvarigt.
 *
 * Denne viewModel har kun til opgave at pass currentIndex til vores FocusImage screen, som giver mere information om en
 * given plakat fra inspiration.
 *
 */

object InspirationViewModel : ViewModel() {
   var currentIndex by mutableStateOf(0)
}