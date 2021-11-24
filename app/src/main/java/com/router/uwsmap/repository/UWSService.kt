package com.router.certificateplatform.repository

import com.router.uwsmap.model.ItemList
import retrofit2.http.GET
import retrofit2.http.Query

interface UWSService {
    companion object{
        const val BASE_URL = "http://api.odcloud.kr/api/15094782/v1/"
    }

    @GET("./uddi:6b2017af-659d-437e-a549-c59788817675")
    suspend fun getInformation(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int,
        @Query("serviceKey",encoded = true) serviceKey: String
    ) : ItemList
}