package com.example.posterlife.view.inspirationView

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*
import java.util.*

/**
 * @Author Kristoffer Pedersen (s205354)
 *
 * @Source https://developer.android.com/jetpack/compose/state#viewmodel-and-jetpack-compose
 * @Source https://proandroiddev.com/jetpack-compose-navigation-architecture-with-viewmodels-1de467f19e1c
 *
 * Compose ser gerne at vi undgår at pass arguments mellem sider, så vidt muligt
 * Derfor giver det god mening at lave vores viewModel til et objekt, hvis selve data staten er irrelevant over flere screens.
 * Vi skal kun bruge en plakatIndex: Int kortvarigt.
 *
 * Denne viewModel har kun til opgave at pass currentIndex til vores FocusImage screen, som giver mere information om en
 * given plakat fra inspiration.
 *
 */

class InspirationViewModel : ViewModel() {

    companion object {
        private var currentIndex by mutableStateOf(0)
        private var favoriteIndexList = mutableListOf<Int>()
    }

    fun getIndex(): Int {
        return currentIndex
    }

    fun setIndex(index: Int) {
        currentIndex = index
    }

    fun setFavorites(index: Int, context: Context) {

        val file = File("favorites").toString()
        val fileOutputStream = context.openFileOutput(file, Context.MODE_PRIVATE)
        favoriteIndexList.add(index)
        val seperator = "-"
        Log.e("SEE HERE", favoriteIndexList.joinToString(seperator))
        val favStr = favoriteIndexList.joinToString(seperator)
        //Log.e("SEE HERE", listOf(favStr.split("-")).toString())
        fileOutputStream.write(favStr.toByteArray())

        getFavorites(context)
    }

    fun getFavorites(context: Context): MutableList<Int> {
        val fileInputStream = context.openFileInput("favorites")
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = bufferedReader.readLine(); text }() != null) {
            stringBuilder.append(text)
        }

        stringBuilder?.let { Log.e("SEE HERE", it.toString()) }

        return mutableListOf<Int>()
    }
}