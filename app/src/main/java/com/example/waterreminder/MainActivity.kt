package com.example.waterreminder

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.waterreminder.ui.screen.MainScreen
import com.example.waterreminder.ui.theme.WaterReminderTheme
import com.google.android.gms.wearable.Wearable

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var clientDataViewModel = ViewModelProvider(this).get(ClientData::class.java)
        clientDataViewModel.startDataClient(this)

        setContent {
            WaterReminderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(this,clientDataViewModel)
                }
            }
        }
    }
}



