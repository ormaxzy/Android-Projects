package com.example.cityweather.models

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
)
