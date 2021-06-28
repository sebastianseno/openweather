package com.dodolife.weather

import androidx.test.core.app.ActivityScenario
import com.dodolife.weather.common.fragment.MainFragmentFactory
import com.dodolife.weather.repo.WeatherRepo
import com.dodolife.weather.ui.splash.SplashActivity
import com.dodolife.weather.util.UtilityClass
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class WeatherTests {

    companion object {
        private const val CITY = "Klaten"
        private const val NUMERIC = "1111"
        private const val Double = 0.0
    }

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Inject
    lateinit var repo: WeatherRepo

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    // Test Scenario When user input City
    @Test
    fun inputCityIfValid() {
        (assert(UtilityClass().isCityValid(CITY)))
    }

    @Test
    fun inputCityIfBlank() {
        assertFalse(UtilityClass().isCityValid(""))

    }
    @Test
    fun inputCityIfNumeric() {
        assertFalse(UtilityClass().isCityValid(NUMERIC))
    }

    // Test Scenario When user failed get latlong data
    @Test
    fun inputLatitudeIsNull() {
        assertFalse(UtilityClass().isLatLongValid(null,Double))
    }
    @Test
    fun inputLongitudeIsNull() {
        assertFalse(UtilityClass().isLatLongValid(Double, null))
    }
    @Test
    fun inputLatLongIsValid() {
        assert(UtilityClass().isLatLongValid(Double, Double))
    }

    //Test scenario Application can open Splash Activity
    @Test
    fun openSplashActivityScenario() {
        val scenario = ActivityScenario.launch(SplashActivity::class.java)
    }
}