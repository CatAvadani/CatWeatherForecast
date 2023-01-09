package com.example.catweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catweatherforecast.model.Favorite
import com.example.catweatherforecast.model.Unit

@Database(entities = [Favorite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDataBase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}