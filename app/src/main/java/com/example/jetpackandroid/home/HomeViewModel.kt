package com.example.jetpackandroid.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackandroid.network.model.PhotosItem
import com.example.jetpackandroid.network.service.HomeApi
import com.example.jetpackandroid.utils.HomeApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val _status = MutableLiveData<HomeApiStatus>()
    val status: LiveData<HomeApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<PhotosItem>>()
    val properties: LiveData<List<PhotosItem>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<PhotosItem>()
    val navigateToSelectedProperty: LiveData<PhotosItem>
        get() = _navigateToSelectedProperty

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getPhotosPropierts()
    }

    private fun getPhotosPropierts() {
        coroutineScope.launch {
            val getPropiertesDeferred = HomeApi.retrofitService.getPhotos()
            try {
                _status.value = HomeApiStatus.LOADING
                val listResult = getPropiertesDeferred.await()
                _status.value = HomeApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                _properties.value = ArrayList()
                _status.value = HomeApiStatus.ERROR
            }
        }
    }

    fun displayPropertyDetails(photosItem: PhotosItem) {
        _navigateToSelectedProperty.value = photosItem
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}