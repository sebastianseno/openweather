package com.dodolife.weather.ui.weather

import androidx.lifecycle.*
import com.dodolife.weather.common.ActionLiveData
import com.dodolife.weather.common.UiState
import com.dodolife.weather.database.entity.WeatherDb
import com.dodolife.weather.repo.WeatherRepo
import com.dodolife.weather.util.UtilityClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModels @Inject constructor(
    private val repo: WeatherRepo
) : ViewModel() {

    val weatherId = MutableLiveData<Int>()

    val allWeather: LiveData<List<WeatherDb>>
        get() = repo.getWeather()

    val findWeather: LiveData<WeatherDb> =
        Transformations.switchMap(weatherId, repo::findWeather)

    val weatherState = ActionLiveData<UiState>()

    val findWeatherState = ActionLiveData<UiState>()

    fun getWeatherLatLong(lat: Double?, lon: Double?, key: String) {
        if (UtilityClass().isLatLongValid(lat, lon)) {
            viewModelScope.launch {
                try {
                    repo.refreshWeather(lat ?: 0.0, lon ?: 0.0, key)
                } catch (error: Exception) {
                    weatherState.sendAction(UiState.Error(error.message.orEmpty()))
                }
            }
        } else {
            weatherState.sendAction(UiState.Error("Failed get your location, please try again"))
        }
    }

    fun getWeatherByCity(city: String, key: String) {
        if (areCityInputtedValid(city)) {
            viewModelScope.launch {
                runCatching {
                    repo.refreshWeatherByCity(city, key)
                }
                    .onSuccess {
                        findWeatherState.postValue(UiState.Success)
                    }.onFailure {
                        findWeatherState.postValue(UiState.Error("City not valid."))
                    }
            }
        }
    }

    private fun areCityInputtedValid(city: String): Boolean {
        return if (!UtilityClass().isCityValid(city)) {
            findWeatherState.postValue(UiState.Error("Kota yang anda masukan salah"))
            false
        } else {
            findWeatherState.postValue(UiState.Success)
            true
        }
    }
}