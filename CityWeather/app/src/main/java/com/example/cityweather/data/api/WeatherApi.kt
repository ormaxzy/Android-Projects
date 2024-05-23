package com.example.cityweather.data.api


import com.example.cityweather.models.City
import com.example.cityweather.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "bf241201cc4a131fe87efbc6a1d9967b"


interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = API_KEY
    ): WeatherResponse

    @GET("geo/1.0/direct")
    suspend fun getGeo(
        @Query("q") city: String,
        @Query("limit") limit: Int = 1,
        @Query("appid") apiKey: String = API_KEY
    ): List<City>
}