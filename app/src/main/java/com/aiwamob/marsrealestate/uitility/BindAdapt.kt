package com.aiwamob.marsrealestate.uitility

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aiwamob.marsrealestate.R
import com.aiwamob.marsrealestate.model.MarsProperty
import com.aiwamob.marsrealestate.ui.home.MarsApiStatus
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
                .placeholder(R.drawable.ic_cached)
                .error(R.drawable.ic_broken_image))
            .into(imageView)
    }
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?){
    when(status){
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_searched)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_cloud_off)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}