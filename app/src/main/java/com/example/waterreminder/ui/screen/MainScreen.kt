package com.example.waterreminder.ui.screen

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.commoncore.helper.FileStorageHelper
import com.example.commoncore.helper.SharedPreferenceHelper
import com.example.waterreminder.ClientData
import com.example.waterreminder.R
import com.example.waterreminder.model.WaterDataModel
import com.example.waterreminder.ui.custom.CircleButton
import com.example.waterreminder.ui.theme.SkyBlue
import kotlinx.coroutines.DelicateCoroutinesApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(context: Context,clientData: ClientData) {

    var controlFlag by remember {
        mutableStateOf(false)
    }

    var targetVolume by remember {
        mutableStateOf(0)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    0.0f to Color(0xFF90D0EE),
                    500.0f to Color(0xFF04407C),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            ),
    ) {
        if(!controlFlag){
            AddTarget(
                context = context,
                value = {
                    targetVolume = it
                },
                flags = {
                    controlFlag = it
                },
                clientData = clientData
            )
        }else{
            AddRecord(
                Modifier.align(BottomEnd),
                targetVolume.toFloat(),
                context,
                flag = {
                    controlFlag = it
                    targetVolume = 0
                }
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddRecord(modifier: Modifier,targetVolume:Float, context: Context,flag:(Boolean)-> Unit){
    val items = remember { mutableStateListOf<WaterDataModel>() }

    val showDialog = remember { mutableStateOf(false) }

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val current = LocalDateTime.now().format(formatter)

    var volume by remember { mutableStateOf(0) }


    val sharedPreferences = SharedPreferenceHelper(context)
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .size(80.dp),
        horizontalAlignment = CenterHorizontally
    ){
        Text(text = "Water Reminder",
            fontSize = 30.sp,
            fontWeight =  FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp),)

        ArcProgressbar(
            volume = volume.toFloat(),
            targetVolume = targetVolume,
            )

        
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally){
            item { 
                Text(text = "Record",
                    fontSize = 24.sp,
                    fontWeight =  FontWeight.Bold,
                    color = Color(0xFFC8E0EC),
                    modifier = Modifier
                        .padding(10.dp))
            }
            items(items){it ->
                WaterItem(it.time,it.water)
            }

        }
    }

    Row (modifier = modifier,){
        Box(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Button(
                modifier = Modifier,
                onClick = {
                    items.clear()
                    flag(false)
                          },
                colors = ButtonDefaults.buttonColors(SkyBlue),
            ){
                Text(text = "Clear",
                    color = Color.Black
                )
            }
        }
        Spacer(Modifier.weight(1f))
        Box(
            modifier = Modifier
                .padding(16.dp)
        ) {
            CircleButton(
                icon = painterResource(R.drawable.add_icon),
                size = 48.dp,
                backgroundColor = SkyBlue,
                onClick = {
                    showDialog.value = true
                }
            )
        }
    }

    if(showDialog.value){
        CustomDialog(setShowDialog = {
            showDialog.value = it
        }) {
            items.add(0,WaterDataModel(current,it.value))
            volume += it.value.toInt()
        }
    }
}



