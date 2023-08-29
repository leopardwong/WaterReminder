package com.example.waterreminder.ui.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp

@Composable
fun CircleButton(
    icon: Painter,
    size: Dp,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(backgroundColor, shape = CircleShape)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(size)
        ) {
            Image(
                painter = icon,
                contentDescription = null,
            )
        }
    }
}
