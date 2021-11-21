package com.router

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.router.certificateplatform.repository.UWSService
import com.router.model.ItemList
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

}