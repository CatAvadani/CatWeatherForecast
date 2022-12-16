package com.example.catweatherforecast.screens.main

import androidx.lifecycle.ViewModel
import com.example.catweatherforecast.data.DataOrException
import com.example.catweatherforecast.model.Weather
import com.example.catweatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {
    suspend fun getWeatherData(city: String): DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city)
    }


}