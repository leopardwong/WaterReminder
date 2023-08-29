package com.example.wearos.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Stepper
import androidx.wear.compose.material.StepperDefaults
import androidx.wear.compose.material.Text
import com.example.wearos.R

@Composable
fun AddRecord(){
    var value by remember { mutableStateOf(250) }
    Stepper(
        value = value,
        onValueChange = { value += 50 },
        increaseIcon = { Icon(StepperDefaults.Increase, "Increase") },
        decreaseIcon = { Icon(StepperDefaults.Decrease, "Decrease") },
        valueProgression = 0..500
    ) { Text("Value: $value ml") }

}