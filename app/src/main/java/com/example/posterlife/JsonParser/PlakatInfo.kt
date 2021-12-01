package com.example.posterlife.JsonParser

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.os.Environment
import androidx.activity.ComponentActivity
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.example.posterlife.MainActivity
import com.example.posterlife.Model.Plakat
import com.example.posterlife.R
import java.io.*
import java.nio.channels.AsynchronousFileChannel.open

/**
 * @Source https://github.com/cbeust/klaxon#streaming-api
 */

class PlakatInfo(context: Context) {

    //private val context = applicationContext


    //private val json = File("./src/main/assets/posterlife.json").inputStream()

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
}
