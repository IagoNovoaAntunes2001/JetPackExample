package com.example.jetpackandroid

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackandroid.home.HomeAdapter
import com.example.jetpackandroid.network.model.PhotosItem

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<PhotosItem>?) {
    val adapter = recyclerView.adapter as HomeAdapter
    adapter.submitList(data)
}