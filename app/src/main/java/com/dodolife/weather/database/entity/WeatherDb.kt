package com.dodolife.weather.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherDb (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val main: String,
    val description: String,
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val tempMax: Double,
    val tempMin: Double
)