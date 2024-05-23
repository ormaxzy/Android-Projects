package com.example.cityweather.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cityweather.presentation.citiesDetailsScreen.CityDetailsViewModel
import com.example.cityweather.repository.WeatherRepository

class CityDetailsViewModelFactory(private val repository: WeatherRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CityDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}