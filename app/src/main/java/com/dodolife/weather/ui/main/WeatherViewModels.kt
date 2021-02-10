package com.dodolife.weather.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dodolife.weather.repo.WeatherRepo
import kotlinx.coroutines.launch

class WeatherViewModels  @ViewModelInject constructor (
    private val repo: WeatherRepo
) : ViewModel() {

    fun getWeatherLatLong(lat:Double?, lon: Double?, key: String) {
        viewModelScope.launch {
            try {
                repo.getWeather(lat?: 0.0, lon?:0.0, key)
            } catch (error: Exception) {

            }
        }
    }
}