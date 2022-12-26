package com.example.catweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catweatherforecast.model.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class WeatherDataBase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}