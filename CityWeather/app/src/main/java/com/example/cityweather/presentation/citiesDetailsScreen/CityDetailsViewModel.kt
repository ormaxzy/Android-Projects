package com.example.cityweather.presentation.citiesDetailsScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityweather.models.Weather
import com.example.cityweather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CityDetailsViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    private val _weatherData: MutableLiveData<List<Weather>?> = MutableLiveData()
    val weatherData: LiveData<List<Weather>?> = _weatherData
    private val _weatherAPIData: MutableLiveData<Weather> = MutableLiveData()
    val weatherAPIData: LiveData<Weather> = _weatherAPIData

    fun searchWeather(city: String) {
        viewModelScope.launch {
            Log.d("@@@", "VM start")
            val weather = withContext(Dispatchers.IO) {
                weatherRepository.getWeather(city)
            }
            _weatherAPIData.value = weather.value
        }
    }

    fun loadWeatherDB(city: String) {
        viewModelScope.launch {
            val weather = withContext(Dispatchers.IO) {
                weatherRepository.getWeatherDB(city)
            }
            _weatherData.value = weather.value
        }
    }
}