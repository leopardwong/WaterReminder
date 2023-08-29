package com.example.wearos.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.CardDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TitleCard
import com.example.wearos.R

@Composable
fun WaterItem(date: String, water: String){
    TitleCard(
        onClick = {  },
        title = { Text("Date & Time: $date") },
        contentColor = MaterialTheme.colors.onSurface,
        titleColor = MaterialTheme.colors.onSurface
    ) {
        Row (modifier = Modifier){
            Image(
                painter = painterResource(R.drawable.water_icon),
                contentDescription = "",
                contentScale = ContentScale.Fit ,
                modifier = Modifier)
            Column(modifier = Modifier
                .align(Alignment.CenterVertically)) {
                Text(text = "Water : $water ml")

            }
        }
    }

}
