package com.aiwamob.marsrealestate.network

import com.aiwamob.marsrealestate.model.MarsProperty
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://mars.udacity.com/"

enum class MarsApiFilter(val value: String){
    SHOW_RENT("rent"), SHOW_BUY("buy"), SHOW_ALL("all")
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface MarsService{
    @GET("realestate")
    fun getPropertiesAsync(@Query("filter") type: String): Deferred<List<MarsProperty>>
}

object MarsApi{
    val retrofitService: MarsService by lazy {
        retrofit.create(MarsService::class.java)
    }
}