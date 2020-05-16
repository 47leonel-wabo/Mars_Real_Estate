package com.aiwamob.marsrealestate.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.aiwamob.marsrealestate.R
import com.aiwamob.marsrealestate.model.MarsProperty

class DetailViewModel(marsProperty: MarsProperty, app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<MarsProperty>()
    val selectedProperty: LiveData<MarsProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = marsProperty
    }

    val displayPropertyPrice = Transformations.map(selectedProperty){
        app.applicationContext.getString(when(it.isRent){
            true -> R.string.display_price_monthly_rental
            false -> R.string.display_price
        }, it.price)
    }

    val displayPropertyType = Transformations.map(selectedProperty){
        app.applicationContext.getString(R.string.display_type,
            app.applicationContext.getString(when(it.isRent){
                true -> R.string.type_rent
                false -> R.string.type_sell
            }))
    }
}