package com.example.catweatherforecast.screens.favorite

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.catweatherforecast.screens.MainScaffold
import com.example.catweatherforecast.widgets.WeatherAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(
    navController: NavController,
   // favoriteViewModel: FavoriteViewModel = hiltViewModel()
){
    
    Text(text = "Favorite Screen")
    /*
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Favorite Cities",
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            navController = navController){ navController.popBackStack() }
    }) {


    }
    
     */
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview(){
    FavoriteScreen(navController = rememberNavController())
}