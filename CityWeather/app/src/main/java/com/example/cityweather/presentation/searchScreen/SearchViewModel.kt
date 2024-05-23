package com.example.cityweather.presentation.searchScreen


import androidx.lifecycle.ViewModel

import com.example.cityweather.repository.WeatherRepository

import javax.inject.Inject

class SearchViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

}
