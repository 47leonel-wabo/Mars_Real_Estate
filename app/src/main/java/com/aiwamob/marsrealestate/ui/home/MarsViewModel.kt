package com.aiwamob.marsrealestate.ui.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiwamob.marsrealestate.model.MarsProperty
import com.aiwamob.marsrealestate.network.MarsApi
import kotlinx.coroutines.*

class MarsViewModel: ViewModel() {

    private val _response = MutableLiveData<String?>()
    val response: LiveData<String?>
        get() = _response

    private val _isNotInternet = MutableLiveData<Boolean>()
    val isNotInternet: LiveData<Boolean>
        get() = _isNotInternet

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    init {
        _isNotInternet.value = false

        viewModelScope.launch {
            getMarsRealEstateProperties()
        }

    }

    private suspend fun getMarsRealEstateProperties() {

        try {
            val deferredList = withContext(Dispatchers.Main){
                MarsApi.retrofitService.getPropertiesAsync()
            }
            val listResult = deferredList.await()
            _response.value = "Success ${listResult.size} Mars elements"
            if (listResult.isNotEmpty()){
                _properties.value = listResult
            }
        }catch (e: Exception){
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