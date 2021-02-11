package com.dodolife.weather.repo

import androidx.lifecycle.LiveData
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

    fun getWeather(): LiveData<List<WeatherDb>> {
        return weatherDao.getWeather()
    }

    fun findWeather(id:Int): LiveData<WeatherDb> {
        return weatherDao.findWeather(id)
    }

    suspend fun refreshWeather(latitude: Double, longitude: Double, key: String) = withContext(Dispatchers.IO) {
        val response = with(weatherServices.getWeatherLatLong(latitude, longitude,"metric", key)) {
            WeatherDb(
                id, weather[0].main, name, weather[0].icon, weather[0].description,  main.feelsLike, main.humidity, main.pressure,
                dt, main.temp, main.tempMax, main.tempMin
            )
        }
        weatherDao.insertWeatherData(response)
    }

    suspend fun refreshWeatherByCity(city:String, key: String) = withContext(Dispatchers.IO) {
        val response = with(weatherServices.getWeatherCity(city,"metric", key)) {
            WeatherDb(
                id, weather[0].main, name,weather[0].icon, weather[0].description, main.feelsLike, main.humidity, main.pressure,
                dt, main.temp, main.tempMax, main.tempMin
            )
        }
        weatherDao.insertWeatherData(response)
    }

}