package com.example.catweatherforecast.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier,
        //  backgroundColor = Color(0xFF64D0FC),
        backgroundColor = Color.Transparent,
        title = {
            Text(
            text = title,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 15.sp
            ) },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = { onAddActionClicked.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = "More Icon",
                        tint = Color.Black

                    )
                }
            } else {
                Box {}
            }
        },
        navigationIcon = {
                         if (icon != null) {
                             Icon(
                                 imageVector = icon,
                                 contentDescription = null,
                                 tint = MaterialTheme.colors.onSecondary,
                                 modifier = Modifier.clickable {
                                     onButtonClicked.invoke()
                                 }
                             )
                         }
        },
        elevation = elevation
    )
}

@Preview(showBackground = true)
@Composable
fun WeatherAppBarPreview() {
    WeatherAppBar(navController = rememberNavController())
}