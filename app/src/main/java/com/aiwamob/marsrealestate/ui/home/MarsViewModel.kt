package com.aiwamob.marsrealestate.ui.home

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

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    private val _selectedProp = MutableLiveData<MarsProperty>()
    val selectedProp: LiveData<MarsProperty>
        get() = _selectedProp

    init {

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

            _status.value = MarsApiStatus.DONE
            if (listResult.isNotEmpty()){
                _properties.value = listResult
            }
        }catch (e: Exception){

            _status.value = MarsApiStatus.ERROR
            _properties.value = ArrayList()
        }

    }

    fun displayPropertyDetail(marsProperty: MarsProperty){
        _selectedProp.value = marsProperty
    }

    fun displayPropertyDetailComplete(){
        _selectedProp.value = null
    }
}