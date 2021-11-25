package com.router.uwsmap

import android.content.ContentValues.TAG
import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.router.certificateplatform.repository.UWSService
import com.router.uwsmap.model.ItemList
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewmodel : ViewModel() {

    private val uwsService: UWSService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(UWSService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        uwsService = retrofit.create(UWSService::class.java)
    }

    //거리순으로 요소수 정보 불러오기
    val itemListLiveData = MutableLiveData<ItemList>()
    val loadingLivedata = MutableLiveData<Boolean>()
    fun fetchUWSList(page: Int, perPage: Int, APIKey: String, myLocation: Location) {
        viewModelScope.launch {

            loadingLivedata.value = true

            var itemList = uwsService.getInformation(page, perPage, APIKey)

            itemList.data.forEach { item ->
                val location = Location("Location")
                location.latitude = item.위도.toDouble()
                location.longitude = item.경도.toDouble()

                val distance = location.distanceTo(myLocation)
                item.거리 = distance.toDouble() / 1000
            }

            itemList.data = itemList.data.sortedBy { it.거리 }
            Log.d(TAG, "fetchUWSList: ${itemList.data.toString()}")


            itemListLiveData.value = itemList
            loadingLivedata.value = false
        }
    }
}
