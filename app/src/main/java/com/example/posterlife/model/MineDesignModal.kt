package com.example.posterlife.model

import com.beust.klaxon.Json

/**
 * Eksisterer kun som model for plakaten.
 */

data class MineDesignModal(
    @Json(path = "$.id") val id: String,
    @Json(path = "$.path") val ImageUrl: String,
)