package com.example.catweatherforecast.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.catweatherforecast.R
import com.example.catweatherforecast.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(8f)
                    .getInterpolation(it)
            })
        )
        delay(2000L)
        navController.navigate(WeatherScreens.MainScreen.name)
    })
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .size(330.dp)
                    .padding(15.dp)
                    .scale(scale.value),
                shape = CircleShape,
                color = Color.White,
                border = BorderStroke(width = 2.dp, color = Color.LightGray)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(1.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sun),
                        contentDescription = "Sunny image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(95.dp)
                    )
                    Text(
                        "Find the sun?",
                        style = MaterialTheme.typography.h5,
                        color = Color.LightGray
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun WeatherSplashScreenPreview() {
    WeatherSplashScreen(navController = rememberNavController())
}