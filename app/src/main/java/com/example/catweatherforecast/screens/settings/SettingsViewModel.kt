package com.example.catweatherforecast.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catweatherforecast.model.Favorite
import com.example.catweatherforecast.model.Unit
import com.example.catweatherforecast.repository.WeatherDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel  @Inject constructor(private val repository: WeatherDBRepository) :
    ViewModel() {
        private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
        val unitList = _unitList.asStateFlow()

        init {
            viewModelScope.launch(Dispatchers.IO) {

                repository.getUnits().distinctUntilChanged()
                    .collect { listOfUnits ->
                        if (listOfUnits.isEmpty()) {
                            Log.d("TAG", ":Empty favorites")
                        } else {
                            _unitList.value = listOfUnits
                            Log.d("FAV", "${unitList.value}")

                        }
                    }
            }
        }
        fun insertUnit(unit: Unit) =
            viewModelScope.launch { repository.insertUnit(unit) }
        fun updateUnit(unit: Unit) =
            viewModelScope.launch { repository.updateUnit(unit) }
        fun deleteUnit(unit: Unit) =
            viewModelScope.launch { repository.deleteUnit(unit) }
        fun deleteAllUnits() =
            viewModelScope.launch { repository.deleteAllUnits() }
}