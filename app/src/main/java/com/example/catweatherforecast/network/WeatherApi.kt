package com.example.catweatherforecast.network

import com.example.catweatherforecast.model.Weather
import com.example.catweatherforecast.model.WeatherObject
import com.example.catweatherforecast.utils.Constants
import com.example.catweatherforecast.utils.Credential
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Qualifier
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET("data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = Credential.API_KEY
    ): Weather
}