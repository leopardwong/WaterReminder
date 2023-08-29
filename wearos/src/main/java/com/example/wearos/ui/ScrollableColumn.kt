package com.example.wearos.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.items
import androidx.wear.compose.material.rememberScalingLazyListState
import androidx.wear.compose.material.scrollAway
import com.example.commoncore.helper.FileStorageHelper
import com.example.commoncore.helper.SharedPreferenceHelper
class WaterDataModel (val time: String, val water: String)
@Composable
fun ScrollableColumn(context: Context) {

    val lazyListState = rememberScalingLazyListState()
    val items = remember { mutableStateListOf<WaterDataModel>() }
    items.add(WaterDataModel("1","123"))
    items.add(WaterDataModel("2","312"))
    items.add(WaterDataModel("3","123"))
    items.add(WaterDataModel("4","321"))
    Scaffold(
        positionIndicator = {
            PositionIndicator(scalingLazyListState = lazyListState)
        },
        timeText = {
            TimeText(Modifier.scrollAway(lazyListState))
        },
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        },
    ){
        ScalingLazyColumn(
            modifier = Modifier
                .fillMaxSize()
                //.rotaryWithScroll(focusRequester, scrollableState = lazyListState)
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            state = lazyListState,
        ) {

            item {
                Text(text = "Record")
            }
            items(items) { item ->
                WaterItem(date = item.time, water = item.water)
            }
            item{
                Button(onClick = {
                    println("======")
                }) {
                    Text(text = "Add")
                }
            }
        }
    }

}
