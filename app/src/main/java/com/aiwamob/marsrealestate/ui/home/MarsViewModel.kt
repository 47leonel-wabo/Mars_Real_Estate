package com.aiwamob.marsrealestate.ui.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiwamob.marsrealestate.model.MarsProperty
import com.aiwamob.marsrealestate.network.MarsApi
import kotlinx.coroutines.*

enum class MarsApiStatus{LOADING, ERROR, DONE}

class MarsViewModel: ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus?>
        get() = _status

/*    private val _isNotInternet = MutableLiveData<Boolean>()
    val isNotInternet: LiveData<Boolean>
        get() = _isNotInternet*/

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    init {
        //_isNotInternet.value = false

        viewModelScope.launch {
            getMarsRealEstateProperties()
        }

    }

    private suspend fun getMarsRealEstateProperties() {

        try {
            val deferredList = withContext(Dispatchers.Main){
                MarsApi.retrofitService.getPropertiesAsync()
            }
            _status.value = MarsApiStatus.LOADING
            val listResult = deferredList.await()
            //status.value = "Success ${listResult.size} Mars elements"
            _status.value = MarsApiStatus.DONE
            if (listResult.isNotEmpty()){
                _properties.value = listResult
            }
        }catch (e: Exception){
            //_isNotInternet.value = true
            _status.value = MarsApiStatus.ERROR
            _properties.value = ArrayList()
        }

    }

/*    fun imageVisible(boolean: Boolean): Int{
        if (boolean){
            return View.VISIBLE
        }
        return View.GONE
    }*/
}