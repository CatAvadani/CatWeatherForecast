package com.example.catweatherforecast.repository

import com.example.catweatherforecast.data.WeatherDao
import com.example.catweatherforecast.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDBRepository @Inject constructor(private val weatherDao: WeatherDao){
    //here we are going to mirror the functions form weatherDao
    fun getFavorite(): Flow<List<Favorite>> = weatherDao.getFavorite()
    suspend fun getFabById(city: String): Favorite = weatherDao.getFabById(city)
    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()
    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)




}