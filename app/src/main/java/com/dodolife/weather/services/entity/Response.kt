package com.dodolife.weather.services.entity

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("status") val status: String,
    @SerializedName("data") val results: T
)

data class ErrorData(
    @SerializedName("error")
    val error: Error?
)

data class Error(
    @SerializedName("errors")
    val errors: List<String>?
)