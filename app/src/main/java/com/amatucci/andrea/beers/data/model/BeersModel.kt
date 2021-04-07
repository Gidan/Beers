package com.amatucci.andrea.beers.data.model

import com.squareup.moshi.Json

data class Beer(
    val id: Int,
    val name: String,
    @Json(name = "tagline")
    val tagLine: String,
    @Json(name = "first_brewed")
    val firstBrewed: String,
    val description: String,
    @Json(name = "image_url")
    val imageUrl: String?,
    val abv: Double,
    val ebc: Int?
)