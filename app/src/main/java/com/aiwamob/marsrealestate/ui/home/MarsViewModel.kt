package com.aiwamob.marsrealestate.ui.home

import android.os.Handler
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aiwamob.marsrealestate.network.MarsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarsViewModel: ViewModel() {

    private val _response = MutableLiveData<String?>()
    val response: LiveData<String?>
        get() = _response

    private val _isNotInternet = MutableLiveData<Boolean>()
    val isNotInternet: LiveData<Boolean>
        get() = _isNotInternet

    init {
        _isNotInternet.value = false
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {

        MarsApi.retrofitService.getProperties().enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure ${t.message}"
                _isNotInternet.value = true
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }

        })
        _response.value = "Fetching data to internet..."
    }

    fun imageVisible(boolean: Boolean): Int{
        if (boolean){
            return View.VISIBLE
        }
        return View.GONE
    }
}