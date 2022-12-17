package com.example.catweatherforecast.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.catweatherforecast.R
import com.example.catweatherforecast.data.DataOrException
import com.example.catweatherforecast.model.Weather
import com.example.catweatherforecast.model.WeatherItem
import com.example.catweatherforecast.screens.main.MainViewModel
import com.example.catweatherforecast.utils.formatDate
import com.example.catweatherforecast.utils.formatDecimals
import com.example.catweatherforecast.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    val weatherData =
        produceState<DataOrException<Weather, Boolean, Exception>>(
            initialValue = DataOrException(
                loading = true
            )) {
            value = mainViewModel.getWeatherData(city = "Bucharest")
        }.value
    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {

        Surface(modifier = Modifier.fillMaxSize(),color = Color(0xE1FF6538)) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainScaffold(weather = weatherData.data!!, navController)
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(title = weather.city?.name.toString() +", ${weather.city?.country}",
            navController = navController, elevation = 5.dp) {
            Log.d("Tag", "MainScaffold: Button Clicked")
        }
    }, backgroundColor = Color.White) {
        MainContent(data = weather)
    }

}

@Composable
fun MainContent(data: Weather) {
    val weatherItem = data.list?.get(0)
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem?.weather?.get(0)?.icon}.png"
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = formatDate(weatherItem?.dt.toString().toInt()),
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(6.dp)
        )
        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageUrl = imageUrl)
                Text(text = formatDecimals(weatherItem?.temp?.day.toString().toDouble()) + "\u00B0", style = MaterialTheme.typography.h4, fontWeight = FontWeight.ExtraBold)
                Text(text = weatherItem?.weather?.get(0)?.main.toString(), fontStyle = FontStyle.Italic)
            }
        }
        HumidityWindPressureRow(weather = weatherItem)
    }
}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem?) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity icon",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = weather?.humidity.toString() + "%",
                color = Color.Black,
                style = MaterialTheme.typography.caption
            )
        }
        Row(modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure icon",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = "${weather?.pressure} psi" ,
                color = Color.Black,
                style = MaterialTheme.typography.caption
            )
        }
        Row(modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind icon",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = "${weather?.humidity} mph",
                color = Color.Black,
                style = MaterialTheme.typography.caption
            )
        }

        
    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    
    Image(painter = rememberAsyncImagePainter(
        model = imageUrl),
        contentDescription = "Weather Icon",
        modifier = Modifier.size(80.dp)
    )

}

@Preview
@Composable
fun MainScreenPreview() {

    HumidityWindPressureRow(weather = WeatherItem())
}