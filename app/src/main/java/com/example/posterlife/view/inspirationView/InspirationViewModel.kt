package com.example.posterlife.view.inspirationView

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.io.*

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
 * Til håndtering af at kunne gemme listerne til strings.
 * @Source https://www.techiedelight.com/convert-list-to-string-kotlin/
 *
 * Til at gemme filer og læse
 * @Source https://www.javatpoint.com/kotlin-android-read-and-write-internal-storage
 */

class InspirationViewModel : ViewModel() {

    companion object {
        var currentIndex by mutableStateOf(0)
        private var favoriteIndexList = mutableListOf<Int>()
    }

    fun getIndex(): Int {
        return currentIndex
    }

    fun setIndex(index: Int) {
        currentIndex = index
    }

    //Inspiration til at sklrive til fil: https://www.javatpoint.com/kotlin-android-read-and-write-internal-storage
    fun setFavorites(index: Int, context: Context) {

        val file = File("favorites").toString()
        val fileOutputStream = context.openFileOutput(file, Context.MODE_PRIVATE)
        if (index != -1) {
        favoriteIndexList.add(index)}
        val seperator = "-"
        Log.e("Liste før read", favoriteIndexList.joinToString(seperator))
        val favStr = favoriteIndexList.joinToString(seperator)
        fileOutputStream.write(favStr.toByteArray())
        getFavorites(context)
    }

    //Inspiration til at læse fra fil: https://www.javatpoint.com/kotlin-android-read-and-write-internal-storage
    fun getFavorites(context: Context): MutableList<Int> {
        val fileInputStream = context.openFileInput("favorites")
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder = StringBuilder()
        var text: String?
        while (run {
                text = bufferedReader.readLine()
                text
            } != null) {
            stringBuilder.append(text)
        }

        stringBuilder.let { Log.e("Liste efter read", it.toString()) }
        if (stringBuilder.isNotEmpty()) {
        val favoritListeTemp = stringBuilder.split("-")
        val favoritListe = favoritListeTemp.map { it.toInt() }
        favoriteIndexList = favoritListe.toMutableList()}

        return favoriteIndexList
    }

    fun fjernFavorite(index: Int, context: Context){
        favoriteIndexList.remove(favoriteIndexList[index])
        //Så vi kan gemme det.
        setFavorites(-1, context)
        Log.e("Liste efter delete", favoriteIndexList.toString())
    }
}