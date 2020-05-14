package com.aiwamob.marsrealestate.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MarsViewModel: ViewModel() {

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        _response.value = "Mars..."
    }

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

}