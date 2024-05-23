package com.example.cityweather.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Weather")
data class Weather(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val city: String,
    val temperature: Double,
    val description: String,
    val icon: String,
    val date: Long
)

