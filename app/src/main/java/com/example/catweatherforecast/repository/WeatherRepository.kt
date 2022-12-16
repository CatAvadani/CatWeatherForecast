package com.example.catweatherforecast.repository

import android.util.Log
import com.example.catweatherforecast.data.DataOrException
import com.example.catweatherforecast.model.Weather
import com.example.catweatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
          api.getWeather(query = cityQuery)
        } catch (e: Exception){
            Log.d("REX", "getWeather: $e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getWeather: $response")
        return DataOrException(data = response)
    }
}