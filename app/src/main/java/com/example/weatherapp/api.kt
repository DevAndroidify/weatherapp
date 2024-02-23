package com.example.weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val baseurl="https:api.openweathermap.org/"
interface api {
    @GET("data/2.5/weather?")
    fun getdat(
        @Query("q")country:String,
       @Query("appid")appid:String
    ): Call<weather>
}