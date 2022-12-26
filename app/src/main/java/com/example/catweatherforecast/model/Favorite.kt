package com.example.catweatherforecast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "fav_tbl")
data class Favorite(
    @Nonnull
    @PrimaryKey
    @ColumnInfo(name = "City")
    val city: String,
    @ColumnInfo(name = "Country")
    val country: String,

    )
