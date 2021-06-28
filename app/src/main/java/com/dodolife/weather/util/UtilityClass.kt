package com.dodolife.weather.util

import java.util.regex.Pattern

open class UtilityClass {
    private val CITY = Pattern.compile(
        "^[a-zA-Z]+\$",
        Pattern.CASE_INSENSITIVE
    )

    open fun isCityValid(cityName: String): Boolean {
        return if (cityName.isEmpty()) {
            false
        } else CITY.matcher(cityName).matches()
    }

    open fun isLatLongValid(lat: Double?, long: Double?): Boolean {
        return !(lat == null || long == null)
    }
}
