package com.example.wearos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
fun calculate(
    volume: Float,
    targetVolume : Float
) : Float {
    return (volume / targetVolume)
}val progressLimit = 300f
@Composable
fun ProgressBar(){
    val progress = calculate(100F,700F) // Example progress value between 0 and 1
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxSize(),
            trackColor = Color.Gray, //back
            strokeWidth = 5.dp ,
            startAngle =300f,
            endAngle = 240f,
            indicatorColor = Color.Red,//front
        )
        ScalingLazyColumn( ){
            item { 
                Text(text = "Target")
            }
            item {
                Row(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "100",
                        color = Color.White,
                        fontSize = 26.sp,
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        text = "/700",
                        color = Color.White,
                        fontSize = 15.sp,
                    )
                    Text(
                        text = "ml",
                        color = Color.White,
                        fontSize = 9.sp
                    )
                } 
            }
        }
    }
}



