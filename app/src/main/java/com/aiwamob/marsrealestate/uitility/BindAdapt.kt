package com.aiwamob.marsrealestate.uitility

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aiwamob.marsrealestate.R
import com.aiwamob.marsrealestate.model.MarsProperty
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("isNotInternetConnection")
fun ImageView.isInternetConnection(internet: Boolean){
    if (!internet){
        setImageResource(R.drawable.ic_cloud_off)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsProperty>?){
    val adapter = recyclerView.adapter as MarsPhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val img = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(img)
            .apply(RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_cloud_off))
            .into(imageView)
    }
}