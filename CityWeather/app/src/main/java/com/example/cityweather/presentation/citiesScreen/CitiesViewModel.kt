package com.example.cityweather.presentation.citiesScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cityweather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CitiesViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    private val _cities: MutableLiveData<List<String>> = MutableLiveData()
    val cities: LiveData<List<String>> = _cities

    init {
        loadCities()
    }

    private fun loadCities() {
        viewModelScope.launch {
            val names = withContext(Dispatchers.IO) {
                weatherRepository.getAllCities()
            }
            _cities.value = names
        }
    }
}