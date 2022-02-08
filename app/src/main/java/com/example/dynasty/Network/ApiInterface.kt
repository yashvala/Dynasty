package com.example.dynasty.Network

import com.example.dynasty.Models.BusinessData
import com.example.dynasty.Models.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("GetCategoryList")
    fun getPhotos(): Call<Data>

    @GET("GetBusinessList")
    fun getBackground(): Call<BusinessData>

    @GET("GetBusinessList")
    fun getBackground(@Query("CategoryId") categoryId:Int): Call<BusinessData>

    @GET("GetBusinessbyId/{id}")
    fun getBusinessesById(@Path("id") businessId : Int): Call<BusinessData>
}