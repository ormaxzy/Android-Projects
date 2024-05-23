package com.example.cityweather.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cityweather.models.Weather

@Database(entities = [Weather::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}