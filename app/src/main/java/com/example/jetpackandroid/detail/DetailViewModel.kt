package com.example.jetpackandroid.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackandroid.network.model.PhotosItem
import com.example.jetpackandroid.repository.detail.DetailRepositoryContract
import com.example.jetpackandroid.utils.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailViewModel(
    application: Application,
    photosItem: PhotosItem,
    private val repository: DetailRepositoryContract
) :
    AndroidViewModel(application) {

    private val _selectedProperty = MutableLiveData<PhotosItem>()
    val selectedProperty: LiveData<PhotosItem>
        get() = _selectedProperty

    private val _statusMessage = MutableLiveData<Event<String>>()
    val statusMessage: LiveData<Event<String>>
        get() = _statusMessage

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        _selectedProperty.value = photosItem
    }

    fun deleteButton(idToDelete: Int) {
        coroutineScope.launch {
            val deleteItem = repository.deleteDetail(idToDelete)
            try {
                deleteItem.await()
                _statusMessage.value = Event("Deleted has been with success!")
            } catch (e: Exception) {
                _statusMessage.value = Event("Deleted Error! ${e.printStackTrace()}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}