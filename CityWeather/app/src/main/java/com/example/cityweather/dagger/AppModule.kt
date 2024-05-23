package com.example.cityweather.dagger

import android.content.Context
import androidx.room.Room
import com.example.cityweather.data.SharedPrefsManager
import com.example.cityweather.data.api.WeatherApi
import com.example.cityweather.data.db.WeatherDao
import com.example.cityweather.data.db.WeatherDatabase
import com.example.cityweather.presentation.citiesScreen.CitiesViewModel
import com.example.cityweather.presentation.citiesDetailsScreen.CityDetailsViewModel
import com.example.cityweather.presentation.searchScreen.SearchViewModel
import com.example.cityweather.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class AppModule(private val context: Context) {
    @Provides
    fun provideWeatherApi(): WeatherApi {
        val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }).build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideWeatherDatabase(context: Context): WeatherDatabase {
        return Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_database")
            .build()
    }

    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao {
        return weatherDatabase.weatherDao()
    }

    @Provides
    fun provideSharedPrefsManager(context: Context): SharedPrefsManager {
        return SharedPrefsManager(context)
    }

    @Provides
    fun provideWeatherRepository(
        weatherApi: WeatherApi,
        weatherDao: WeatherDao
    ): WeatherRepository {
        return WeatherRepository(weatherApi, weatherDao)
    }

    @Provides
    fun provideWeatherViewModel(weatherRepository: WeatherRepository): SearchViewModel {
        return SearchViewModel(weatherRepository)
    }

    @Provides
    fun provideCityDetailsViewModel(weatherRepository: WeatherRepository): CityDetailsViewModel {
        return CityDetailsViewModel(weatherRepository)
    }

    @Provides
    fun provideCitiesViewModel(weatherRepository: WeatherRepository): CitiesViewModel {
        return CitiesViewModel(weatherRepository)
    }
}