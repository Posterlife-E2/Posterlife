package com.example.posterlife.Model

import com.beust.klaxon.Json

/**
 * Eksisterer kun som model for plakaten.
 */

data class Plakat(
    @Json(path = "$.Title") val title: String,
    @Json(path = "$.Description") val description: String,
    @Json(path = "$.ImageUrl") val imageURL: String,
    @Json(path = "$.Price 70x100") val price70x100: Int,
    @Json(path = "$.Price 50x70") val price50x70: Int,
    @Json(path = "$.Price A3") val priceA3: Int
)
