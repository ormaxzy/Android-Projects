package com.example.cityweather.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cityweather.models.Weather

@Dao
interface WeatherDao {
    @Query("SELECT DISTINCT city FROM Weather")
    fun getAllCities(): List<String>

    @Query("SELECT * FROM Weather WHERE city = :city AND date = :date")
    fun getWeather(city: String, date: Long): Weather?

    @Query("SELECT * FROM Weather WHERE city = :city")
    fun getAllWeather(city: String): List<Weather>

    @Query("DELETE FROM Weather WHERE city = :city")
    suspend fun deleteAllForCity(city: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weather: Weather)
}
