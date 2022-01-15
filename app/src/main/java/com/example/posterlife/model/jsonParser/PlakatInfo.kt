package com.example.posterlife.model.jsonParser

import android.content.Context
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.example.posterlife.model.Plakat
import java.io.*

/**
 * @Author Kristoffer Pedersen s205354
 *
 * @Source https://github.com/cbeust/klaxon#streaming-api
 */

class PlakatInfo(context: Context) {

    private val json = context.assets.open("posterlife.json")


    fun getPlakatInfo(): ArrayList<Plakat> {


        val klaxon = Klaxon()

        JsonReader(InputStreamReader(json)).use { reader ->

            val plakatResult = arrayListOf<Plakat>()
            reader.beginArray {
                while (reader.hasNext()) {
                    val plakat = klaxon.parse<Plakat>(reader)
                    plakat?.let { plakatResult.add(it) }
                }
            }
            return plakatResult
        }
    }


    fun searchPlakat(query: String, array: ArrayList<Plakat> = getPlakatInfo()): ArrayList<Plakat> {
        val filteredList = ArrayList<Plakat>()
        array.forEach { Plakat ->
            //needs to take in query from searchbar in inspiration
            if (Plakat.title.contains(query)) {
                filteredList.add(Plakat)
            }
        }
        return filteredList
    }
}
