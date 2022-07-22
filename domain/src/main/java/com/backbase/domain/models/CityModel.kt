package com.backbase.domain.models

data class CityModel (
    val country: String,
    val name: String,
    val coord: CoordinatesModel
) {
    val sortedKey = name.lowercase()
}

data class CoordinatesModel(val longitude: Double, val latitude: Double)

