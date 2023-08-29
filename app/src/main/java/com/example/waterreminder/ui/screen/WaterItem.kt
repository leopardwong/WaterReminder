package com.example.waterreminder.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.waterreminder.R

@Composable
fun WaterItem(date: String, water: String){
    Box(modifier = Modifier
        .fillMaxWidth()
        .size(128.dp)
        .clip(RoundedCornerShape(20))
        .padding(vertical = 10.dp)
        .background(Color(0xFF77D8E4)),
        ){
        Row (modifier = Modifier.align(Alignment.CenterStart)){
            Image(
                painter = painterResource(R.drawable.water_icon),
                contentDescription = "",
                contentScale = ContentScale.Fit ,
                modifier = Modifier.size(75.dp))
            Column(modifier = Modifier
                .align(Alignment.CenterVertically)) {
                Text(text = "Date & Time: $date")
                Text(text = "Water : $water ml")

            }
        }

    }
}
