package com.example.waterreminder

import android.content.Context
import android.util.Log
import com.example.commoncore.helper.FileStorageHelper
import com.example.commoncore.helper.SharedPreferenceHelper
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.WearableListenerService

class DataLayerWearListenerService: WearableListenerService() {
    override fun onDataChanged(dataEvents: DataEventBuffer) {

    }
}

