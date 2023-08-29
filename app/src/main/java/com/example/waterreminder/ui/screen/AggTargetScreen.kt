package com.example.waterreminder.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.waterreminder.ClientData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTarget(value:(Int) -> Unit, flags: (Boolean)-> Unit, context: Context, clientData: ClientData){
    var txtField by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TextField(
            modifier = Modifier.padding(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = txtField,
            placeholder = { Text(text = "Enter Volume") },
            onValueChange = {
                txtField = it
            })
        Button(onClick = {
            if(!txtField.isEmpty()){
                value(txtField.toInt())
                flags(true)
                clientData.setData(txtField)
            }else{
                Toast.makeText(context, "Please Enter the Volume",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }) {
            Text("Add Target Volume")
        }
    }
}