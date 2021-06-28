package com.dodolife.weather.common.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.dodolife.weather.ui.weather.WeatherDetailFragment
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainFragmentFactory@Inject
constructor(
  ): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {

            WeatherDetailFragment::class.java.name -> {
                val fragment = WeatherDetailFragment()
                fragment
            }

            else -> super.instantiate(classLoader, className)
        }
    }
}