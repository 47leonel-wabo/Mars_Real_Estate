package com.aiwamob.marsrealestate.ui.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiwamob.marsrealestate.network.MarsApi
import kotlinx.coroutines.*

class MarsViewModel: ViewModel() {

    private val _response = MutableLiveData<String?>()
    val response: LiveData<String?>
        get() = _response

    private val _isNotInternet = MutableLiveData<Boolean>()
    val isNotInternet: LiveData<Boolean>
        get() = _isNotInternet

    init {
        _isNotInternet.value = false

        viewModelScope.launch {
            getMarsRealEstateProperties()
        }

    }

    private suspend fun getMarsRealEstateProperties() {

        val deferredList = withContext(Dispatchers.Main){
            MarsApi.retrofitService.getPropertiesAsync()
        }
        val listResult = deferredList.await()
        if (listResult.isNotEmpty()){
            _response.value = "Success ${listResult.size} Mars elements"
        }else{
            _response.value = "Fail to load data or no data!"
            _isNotInternet.value = true
        }
    }

    fun imageVisible(boolean: Boolean): Int{
        if (boolean){
            return View.VISIBLE
        }
        return View.GONE
    }
}