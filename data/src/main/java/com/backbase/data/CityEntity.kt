package com.backbase.data

import com.backbase.domain.models.CityModel
import com.backbase.domain.models.CoordinatesModel
import com.google.gson.annotations.SerializedName

data class CityEntity(
    @SerializedName("country")
    val country: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("_id")
    val _id: Int,
    @SerializedName("coord")
    val coord: CoordEntity
    )

data class CoordEntity(
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("lat")
    val lat: Double
    )

fun CityEntity.toModel() = CityModel(
    country = this.country,
    name = this.name,
    coord = CoordinatesModel(
        longitude = this.coord.lon,
        latitude = this.coord.lat
    )
)
