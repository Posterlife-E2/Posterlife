package com.example.posterlife.Model

/**
 * Eksisterer kun som model for plakaten.
 */

data class Plakat(
    val title: String,
    val description: String,
    val imageURL: String,
    val price70x100: String,
    val price50x70: String,
    val priceA3: String
)
