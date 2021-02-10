package com.dodolife.weather.services.rest

import com.dodolife.weather.services.entity.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServices {

    @GET("weather")
    suspend fun getWeatherLatLong(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ): WeatherData

}