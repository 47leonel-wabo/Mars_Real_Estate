package com.aiwamob.marsrealestate.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://mars.udacity.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MarsService{
    @GET("realestate")
    fun getProperties(): Call<String>
}

object MarsApi{
    val retrofitService: MarsService by lazy {
        retrofit.create(MarsService::class.java)
    }
}