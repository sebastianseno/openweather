package com.dodolife.weather.repo

import com.dodolife.weather.database.KraDb
import com.dodolife.weather.database.dao.WeatherDao
import com.dodolife.weather.database.entity.WeatherDb
import com.dodolife.weather.services.rest.WeatherServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepo @Inject constructor(
    private val kraDb: KraDb,
    private val weatherServices: WeatherServices
) {

    private val weatherDao: WeatherDao = kraDb.weatherDao()

    suspend fun getWeather(latitude: Double, longitude: Double, key: String) = withContext(Dispatchers.IO) {
        val response = with(weatherServices.getWeatherLatLong(latitude, longitude, key)) {
            WeatherDb(
                id, weather[0].main, weather[0].description, main.feelsLike, main.humidity, main.pressure,
                main.temp, main.tempMax, main.tempMin
            )
        }
        weatherDao.insertWeatherData(response)
    }

}