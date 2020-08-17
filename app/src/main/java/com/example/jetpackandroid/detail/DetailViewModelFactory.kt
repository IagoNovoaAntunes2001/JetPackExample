package com.example.jetpackandroid.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackandroid.network.model.PhotosItem
import java.lang.IllegalArgumentException

class DetailViewModelFactory(private val application: Application, private val photoItem: PhotosItem): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(application, photoItem) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}