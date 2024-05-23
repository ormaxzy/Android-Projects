package com.example.cityweather.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cityweather.presentation.citiesScreen.CitiesViewModel
import com.example.cityweather.repository.WeatherRepository

class CitiesViewModelFactory(private val repository: WeatherRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CitiesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CitiesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}