package com.example.cityweather.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cityweather.data.api.WeatherApi
import com.example.cityweather.data.db.WeatherDao
import com.example.cityweather.models.Weather
import com.example.cityweather.models.toWeather
import java.util.Date
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao
) {

    suspend fun getWeather(city: String): LiveData<Weather> {
        Log.d("@@@", "Repo Start")
        val cachedWeather = weatherDao.getWeather(city, Date().time)
        return if (cachedWeather != null) {
            MutableLiveData(cachedWeather)
        } else {
            val lat = weatherApi.getGeo(city)[0].lat
            val lon = weatherApi.getGeo(city)[0].lon
            val newWeather = weatherApi.getWeather(lat, lon).toWeather()
            Log.d("@@@", "Api end")
            weatherDao.insert(newWeather)
            MutableLiveData(newWeather)
        }
    }

    fun getWeatherDB(city: String): MutableLiveData<List<Weather>> {
        val cachedWeather = weatherDao.getAllWeather(city)
        return MutableLiveData(cachedWeather)
    }

    fun getAllCities(): List<String> {
        return weatherDao.getAllCities()
    }

}
