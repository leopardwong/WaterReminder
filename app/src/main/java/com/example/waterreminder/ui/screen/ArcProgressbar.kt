package com.example.waterreminder.ui.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke


val progressLimit = 300f

fun calculate(
    volume: Float,
    targetVolume : Float
) : Float {
    return (volume / targetVolume) * progressLimit
}

@Composable
fun ArcProgressbar(
    modifier: Modifier = Modifier,
    volume: Float,
    targetVolume: Float,
) {
    val volumeAnimatedValue = calculate(volume,targetVolume)
    val volumeAnimate = remember { Animatable(volumeAnimatedValue) }
    val startVolumeAnimate = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(volume) {
        if (volume > 0f) {
            coroutineScope.launch {
                volumeAnimate.animateTo(
                    targetValue = volumeAnimatedValue,
                    animationSpec = tween(
                        durationMillis = 1000
                    )
                )
            }
            // animate volume
            coroutineScope.launch {
                startVolumeAnimate.animateTo(
                    targetValue = volume,
                    animationSpec = tween(
                        durationMillis = 1000
                    )
                )
            }
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            PointsProgress(
                volumeProgress = {
                    if(volumeAnimate.value < progressLimit) volumeAnimate.value else progressLimit // deferred read of progress
                }
            )

            CollectorLevel(
                modifier = Modifier.align(Alignment.Center),
                volume = startVolumeAnimate.value,
                targetVolume = targetVolume
            )
        }
    }
}


@Composable
fun CollectorLevel(
    modifier : Modifier = Modifier,
    volume: Float,
    targetVolume: Float
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = volume.toInt().toString(),
            color = Color.White,
            fontSize = 56.sp,
        )
        Text(
            modifier = Modifier
                .padding(top = 16.dp),
            text = "/${targetVolume .toInt()}",
            color = Color.White,
            fontSize = 30.sp,
        )
        Text(
            text = "ml",
            color = Color.White,
            fontSize = 18.sp
        )
    }
}



@Composable
fun BoxScope.PointsProgress(
    volumeProgress: () -> Float
) {

    val start = 120f
    val end = 300f
    val thickness = 10.dp

    Canvas(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(10.dp)
            .aspectRatio(1f)
            .align(Alignment.Center),
        onDraw = {
            // Background Arc
            drawArc(
                color = Color.LightGray,
                startAngle = start,
                sweepAngle = end,
                useCenter = false,
                style = Stroke(thickness.toPx(), cap = StrokeCap.Square),
                size = Size(size.width, size.height)
            )

            // Foreground Arc
            drawArc(
                color = Color(0xFF00B0FF),
                startAngle = start,
                sweepAngle = volumeProgress(),
                useCenter = false,
                style = Stroke(thickness.toPx(), cap = StrokeCap.Square),
                size = Size(size.width, size.height)
            )
        }
    )
}


