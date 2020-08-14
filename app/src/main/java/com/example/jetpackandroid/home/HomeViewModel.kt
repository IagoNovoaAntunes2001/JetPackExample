package com.example.jetpackandroid.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackandroid.network.model.PhotosItem
import com.example.jetpackandroid.network.service.HomeApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel: ViewModel() {
    private val _properties = MutableLiveData<List<PhotosItem>>()
    val properties: LiveData<List<PhotosItem>>
        get() = _properties

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getPhotosPropierts()
    }

    private fun getPhotosPropierts() {
        coroutineScope.launch {
            val getPropiertesDeferred = HomeApi.retrofitService.getPhotos()
            try {
                val listResult = getPropiertesDeferred.await()
                _properties.value = listResult
            } catch (e: Exception) {
                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}