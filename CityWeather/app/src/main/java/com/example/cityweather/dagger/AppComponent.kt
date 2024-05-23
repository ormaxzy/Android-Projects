package com.example.cityweather.dagger

import com.example.cityweather.presentation.citiesScreen.CitiesFragment
import com.example.cityweather.presentation.citiesDetailsScreen.CityDetailsFragment
import com.example.cityweather.presentation.searchScreen.SearchFragment
import com.example.cityweather.repository.WeatherRepository
import dagger.Component


@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(citiesFragment: CitiesFragment)
    fun inject(cityDetailsFragment: CityDetailsFragment)
    fun inject(searchFragment: SearchFragment)

    fun provideWeatherRepository(): WeatherRepository
}