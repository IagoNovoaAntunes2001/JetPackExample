package com.example.jetpackandroid.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackandroid.network.model.PhotosItem

class DetailViewModel(application: Application, photosItem: PhotosItem) :
    AndroidViewModel(application) {

    private val _selectedProperty = MutableLiveData<PhotosItem>()
    val selectedProperty: LiveData<PhotosItem>
        get() = _selectedProperty

    init {
        _selectedProperty.value = photosItem
    }

}