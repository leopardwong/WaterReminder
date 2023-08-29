package com.example.waterreminder

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.CapabilityInfo
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class ClientData: ViewModel(),
    DataClient.OnDataChangedListener,
    CapabilityClient.OnCapabilityChangedListener  {

    private val _capabilityNodes: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var dataClient: DataClient
    val capabilityNodes: LiveData<Boolean>
        get() = _capabilityNodes

    fun startDataClient(context: Context) {
        dataClient = Wearable.getDataClient(context)
    }
    override fun onDataChanged(p0: DataEventBuffer) {
        println("ClientData ========= onDataChanged")
    }

    override fun onCapabilityChanged(capabilityInfo: CapabilityInfo) {
        _capabilityNodes.value = !capabilityInfo.nodes.isNullOrEmpty()
    }

    companion object {
        private const val USER_INFO_PATH = "/user_info_path"
        private const val VALUE = "/value"
        private const val TAG = "MobileClientDataViewModelCell"
    }

    fun setData(value: String){
        runBlocking{
            try {
                val request = PutDataMapRequest.create(USER_INFO_PATH).apply {
                    dataMap.apply {
                        putString(VALUE, value)
                    }
                }.asPutDataRequest().setUrgent()

                val result = dataClient.putDataItem(request).await()
                println("ClientData ========= setData")
                Log.d(TAG, "DataItem saved: $result")
            } catch (cancellationException: CancellationException) {
                throw cancellationException
            } catch (exception: Exception) {
                Log.d(TAG, "Saving DataItem failed: $exception")
            }
        }


    }
//    fun sendInfoToWear(name: String, heightUser: String, weightUser: String) {
//        runBlocking {
//            try {
//                val request = PutDataMapRequest.create(USER_INFO_PATH).apply {
//                    dataMap.apply {
//                        putString(NAME_USER_INFO_KEY, name)
//                        putString(HEIGHT_USER_INFO_KEY, heightUser)
//                        putString(WEIGHT_USER_INFO_KEY, weightUser)
//                    }
//                }
//                    .asPutDataRequest()
//                    .setUrgent()
//
//                val result = dataClient.putDataItem(request).await()
//
//                Log.d(TAG, "DataItem saved: $result")
//            } catch (cancellationException: CancellationException) {
//                throw cancellationException
//            } catch (exception: Exception) {
//                Log.d(TAG, "Saving DataItem failed: $exception")
//            }
//        }
//    }

}