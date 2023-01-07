package com.example.catweatherforecast.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.catweatherforecast.R
import com.example.catweatherforecast.data.DataOrException
import com.example.catweatherforecast.model.Weather
import com.example.catweatherforecast.model.WeatherItem
import com.example.catweatherforecast.navigation.WeatherScreens
import com.example.catweatherforecast.screens.main.MainViewModel
import com.example.catweatherforecast.utils.formatDate
import com.example.catweatherforecast.utils.formatDateTime
import com.example.catweatherforecast.utils.formatDecimals
import com.example.catweatherforecast.widgets.WeatherAppBar

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
) {
  //  Log.d("City", "MainScreen: $city")

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
            initialValue = DataOrException(
                loading = true
            )) {
            value = mainViewModel.getWeatherData(city = city.toString())
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
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            } ,
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
            color = Color.Black,
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
                Text(text = formatDecimals(weatherItem?.temp?.day.toString().toDouble()) + "\u00B0",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
                Text(text = weatherItem?.weather?.get(0)?.main.toString(),
                    fontStyle = FontStyle.Italic,
                    color = Color.Black
                )
            }
        }
        HumidityWindPressureRow(weather = data.list?.get(0))
        Divider(color = Color.Black)
        SunsetSunriseRow(weather = data.list?.get(0))
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "This Week",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = Color.Black)

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.LightGray,
            shape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10)
            
        ) {
            LazyColumn(
                modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(items = data.list!!) { item: WeatherItem ->
                    RowOfWeekDays(item)
                }
            }
        }


    }
}

@Composable
fun RowOfWeekDays(weather: WeatherItem?) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather?.weather?.get(0)?.icon}.png"

    Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .height(70.dp)
            .clip(
                RoundedCornerShape(
                    topStartPercent = 50,
                    bottomStartPercent = 50,
                    bottomEndPercent = 50
                )
            ),
        color = Color.White,
        elevation = 4.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Text(
                    text = (formatDate(weather?.dt.toString().toInt())).split(",")[0],
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.Black
                )
                WeatherStateImage(imageUrl = imageUrl)
                Surface(
                    modifier = Modifier.wrapContentWidth(),
                    color = Color(0xB7F1B80C),
                    shape = CircleShape
                    
                ) {
                    Text(text = weather?.weather?.get(0)?.description.toString(),
                        color = Color.Black,
                        style = MaterialTheme.typography.caption,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(3.dp)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = weather?.temp?.max?.toInt().toString() + "°",
                        fontWeight = FontWeight.Bold, color = Color.Blue,
                    )
                    Spacer(modifier =Modifier.size(3.dp))
                    Text(
                        text = weather?.temp?.min?.toInt().toString() + "°",
                        fontWeight = FontWeight.Bold, color = Color.LightGray)
                }

            }

        }
    }

  }


@Composable
fun SunsetSunriseRow(weather: WeatherItem?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 6.dp)
    ) {
        Row(modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise icon",
                modifier = Modifier.size(30.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = formatDateTime(weather?.sunrise.toString().toInt()),
                style = MaterialTheme.typography.caption,
                color = Color.Black
            )

        }
        Row(modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = formatDateTime(weather?.sunset.toString().toInt()),
                style = MaterialTheme.typography.caption,
                color = Color.Black
            )
            Spacer(modifier = Modifier.size(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunset icon",
                modifier = Modifier.size(30.dp),
                tint = Color.Black
            )

        }
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
@Preview(showBackground = true)
@Composable
fun MainContentPreview(){
    MainScaffold(weather = Weather(), navController = rememberNavController() )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {

    HumidityWindPressureRow(weather = WeatherItem())
}
@Preview(showBackground = true)
@Composable
fun SunrisePreview() {
   SunsetSunriseRow(weather = WeatherItem())
}