package com.dodolife.weather.ui.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.dodolife.weather.databinding.FragmentWeatherDetailBinding
import com.dodolife.weather.extensions.toDateFormat
import com.dodolife.weather.extensions.toCelciusFormat
import com.dodolife.weather.modules.BaseFragment
import com.dodolife.weather.modules.viewBinding

class WeatherDetailFragment : BaseFragment() {

    private val binding by viewBinding(FragmentWeatherDetailBinding::bind)

    private val viewModel by viewModels<WeatherViewModels>()

    private val args by navArgs<WeatherDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentWeatherDetailBinding.inflate(layoutInflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weatherId.value = args.idWeather

        observeNonNull(viewModel.findWeather) {
            binding.weatherIcon?.load("https://openweathermap.org/img/wn/${it.icon}@2x.png")
            binding.city.text = it.name
            binding.date.text = it.date.toDateFormat()
            binding.temperature.text = it.temp.toCelciusFormat()
            binding.temperatureSummary.text = it.main
            binding.temperatureNext.text = it.description
            binding.minValue.text = it.tempMin.toCelciusFormat()
            binding.maxValue.text = it.tempMax.toCelciusFormat()
            binding.feelsValue.text = it.feelsLike.toCelciusFormat()
            binding.pressureValue.text = it.pressure.toInt().toString()
            binding.humidityValue.text = it.humidity.toInt().toString()
        }
    }
}