package com.example.catweatherforecast.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier.padding(2.dp),
                backgroundColor = Color(0xFFF7F29E)
            ) {
                Row(horizontalArrangement = Arrangement.Start
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription =" Back Arrow" )
                    Text(
                        text = "Search",
                        modifier = Modifier.padding(start = 140.dp),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(35.dp)
            ,
            shape = RoundedCornerShape(25),
            border = BorderStroke(1.dp, color = Color.Black)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Search Screen",
                    modifier = Modifier.padding(4.dp),
                    style = TextStyle(fontSize = 18.sp),
                    color = Color.Black)
            }
        }
    }

}

@Preview
@Composable
fun SearchScreenPreview(){
    SearchScreen(navController = rememberNavController())
}