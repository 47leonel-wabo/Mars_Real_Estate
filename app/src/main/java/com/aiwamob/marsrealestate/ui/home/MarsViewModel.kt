package com.aiwamob.marsrealestate.ui.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aiwamob.marsrealestate.model.MarsProperty
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

        MarsApi.retrofitService.getProperties().enqueue(object : Callback<List<MarsProperty>>{
            override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
                _response.value = "Failure ${t.message}"
                _isNotInternet.value = true
            }

            override fun onResponse(call: Call<List<MarsProperty>>, response: Response<List<MarsProperty>>) {
                _response.value = "Success ${response.body()?.size} Mars elements"
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