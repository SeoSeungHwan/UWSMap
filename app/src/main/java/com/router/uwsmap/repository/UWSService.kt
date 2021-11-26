package com.router.certificateplatform.repository

import com.router.uwsmap.model.ItemList
import retrofit2.http.GET
import retrofit2.http.Query

interface UWSService {
    companion object{
        const val BASE_URL = "http://api.odcloud.kr/api/uws/v1/"
    }

    @GET("inventory")
    suspend fun getInformation(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int,
        @Query("serviceKey",encoded = true) serviceKey: String
    ) : ItemList
}