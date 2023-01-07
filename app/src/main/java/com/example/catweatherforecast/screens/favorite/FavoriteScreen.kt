package com.example.catweatherforecast.screens.favorite

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.catweatherforecast.model.Favorite
import com.example.catweatherforecast.navigation.WeatherScreens
import com.example.catweatherforecast.widgets.WeatherAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
){
    
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Favorite Cities",
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            navController = navController){ navController.popBackStack() }
    }) {
        Surface(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier) {
                val listOfFavorite = favoriteViewModel.favList.collectAsState().value
                LazyColumn() {
                    items(items = listOfFavorite) { favorite ->
                        RowOfFavorites(favorite, navController, favoriteViewModel)

                    }
                }
            }
        }


    }
    

}

@Composable
fun RowOfFavorites(favorite: Favorite, navController: NavController, favoriteViewModel: FavoriteViewModel) {

    Surface(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .height(50.dp)
        .clickable {
             navController.navigate(WeatherScreens.MainScreen.name+"/${favorite.city}")
        },
        color = Color(0xFFB2dfdb),
        shape = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50, bottomEndPercent = 50)
    ){
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = favorite.city,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 4.dp))
            Surface(modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFd1E3E1)
            ) {
                Text(text = favorite.country,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )
            }
            Icon(imageVector = Icons.Default.Delete,
                contentDescription = "Delete Icon",
                tint = Color.Red.copy(0.3f),
                modifier = Modifier.clickable {
                    favoriteViewModel.deleteFavorite(favorite)
                }
            )
        }
    }

}


