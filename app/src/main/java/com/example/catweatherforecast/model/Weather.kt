package com.example.catweatherforecast.model


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("city")
    val city: City? = City(),
    @SerializedName("cnt")
    val cnt: Int? = 0,
    @SerializedName("cod")
    val cod: String? = "",
    @SerializedName("list")
    val list: List<WeatherObject>? = listOf(),
    @SerializedName("message")
    val message: Double? = 0.0
)