package com.example.cityweather.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("weather") val weather: List<WeatherData>,
    @SerializedName("main") val main: Main,
    @SerializedName("dt") val dt: Long,
    @SerializedName("name") val name: String
)

data class WeatherData(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class Main(
    @SerializedName("temp") val temp: Double
)

fun WeatherResponse.toWeather(): Weather {
    val weatherData = this.weather.firstOrNull()
    return Weather(
        city = this.name,
        temperature = this.main.temp,
        description = weatherData?.description ?: "N/A",
        icon = weatherData?.icon ?: "N/A",
        date = this.dt
    )
}
