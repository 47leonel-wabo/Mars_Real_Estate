package com.aiwamob.marsrealestate.uitility

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.aiwamob.marsrealestate.R
import com.bumptech.glide.Glide

@BindingAdapter("isNotInternetConnection")
fun ImageView.isInternetConnection(internet: Boolean){
    if (!internet){
        setImageResource(R.drawable.ic_cloud_off)
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val img = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(img)
            .into(imageView)
    }
}