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

    private val _singleProperty = MutableLiveData<MarsProperty>()
    val singleProperty: LiveData<MarsProperty>
        get() = _singleProperty

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
        val error = deferredList.getCompletionExceptionOrNull()?.cause
        if (error != null) {
            _response.value = "Fail: $error"
            _isNotInternet.value = true
        }else{
            if (listResult.isNotEmpty()){
                _singleProperty.value = listResult[0]
                _response.value = "Success ${listResult.size} Mars elements"
            }else{
                _response.value = "Fail to load data or no data!"
                _isNotInternet.value = true
            }
        }

    }

    fun imageVisible(boolean: Boolean): Int{
        if (boolean){
            return View.VISIBLE
        }
        return View.GONE
    }
}