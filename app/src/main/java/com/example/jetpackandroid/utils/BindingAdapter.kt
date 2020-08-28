package com.example.jetpackandroid.utils

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jetpackandroid.R
import com.example.jetpackandroid.network.model.PhotosItem
import com.example.jetpackandroid.ui.home.adapter.HomeAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_connection_error)
            )
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<PhotosItem>?) {
    val adapter = recyclerView.adapter as HomeAdapter
    adapter.submitList(data)
}

@BindingAdapter("homeApiStatus")
fun bindStatus(statusImageView: ImageView, status: HomeApiStatus?) {
    when (status) {
        HomeApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        HomeApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        HomeApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("homeApiStatusTextView")
fun bindStatusTexts(statusEditText: TextView, status: HomeApiStatus?) {
    when (status) {
        HomeApiStatus.LOADING -> {
            statusEditText.visibility = View.GONE
        }
        HomeApiStatus.ERROR -> {
            statusEditText.visibility = View.GONE
        }
        HomeApiStatus.DONE -> {
            statusEditText.visibility = View.VISIBLE
        }
    }
}