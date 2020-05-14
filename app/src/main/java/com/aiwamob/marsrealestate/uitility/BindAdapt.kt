package com.aiwamob.marsrealestate.uitility

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.aiwamob.marsrealestate.R

@BindingAdapter("isNotInternetConnection")
fun ImageView.isInternetConnection(internet: Boolean){
    if (!internet){
        setImageResource(R.drawable.ic_cloud_off)
    }
}