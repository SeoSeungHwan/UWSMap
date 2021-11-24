package com.router.uwsmap

import android.location.Location
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

    //요소수 정보 불러오기
    val itemListLiveData = MutableLiveData<ItemList>()
    fun fetchUWSList(page: Int , perPage: Int , APIKey : String){
        viewModelScope.launch {
            itemListLiveData.value = uwsService.getInformation(page,perPage,APIKey)
        }
    }

    fun sortDistanceUWSList(lat : Double,lon : Double){
        val startLocation = Location("startLocation")
        startLocation.latitude = lat
        startLocation.longitude = lon

        val itemList = itemListLiveData.value
        itemList!!.data.sortedWith(Comparator { a, b ->
            val aLocation = Location("aLocation")
            aLocation.latitude = a.위도.toDouble()
            aLocation.longitude = a.경도.toDouble()

            val bLocation = Location("bLocation")
            bLocation.latitude = b.위도.toDouble()
            bLocation.longitude = b.경도.toDouble()

            val aDistance = startLocation.distanceTo(aLocation)
            val bDistance = startLocation.distanceTo(bLocation)
            when {
                aDistance > bDistance -> 1
                aDistance < bDistance -> -1
                else -> 0
            }
        })

        itemListLiveData.value = itemList
    }


}