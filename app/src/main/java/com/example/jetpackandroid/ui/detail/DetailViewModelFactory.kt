package com.example.jetpackandroid.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackandroid.network.model.PhotosItem
import com.example.jetpackandroid.repository.detail.DetailRepositoryContract

class DetailViewModelFactory(
    private val application: Application,
    private val photoItem: PhotosItem,
    private val repository: DetailRepositoryContract
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(
                application,
                photoItem,
                repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}