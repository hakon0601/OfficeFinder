package com.example.officefinder.api

import com.example.officefinder.models.OfficeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("offices")
    fun getOffices(@Query("username") username: String): Call<List<OfficeModel>>

}