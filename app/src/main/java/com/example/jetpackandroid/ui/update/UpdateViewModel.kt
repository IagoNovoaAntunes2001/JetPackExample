package com.example.jetpackandroid.ui.update

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackandroid.network.model.PhotosItem
import com.example.jetpackandroid.repository.update.UpdateRepositoryContract
import com.example.jetpackandroid.utils.Event
import com.example.jetpackandroid.utils.verifyFields
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UpdateViewModel(
    application: Application,
    private val repository: UpdateRepositoryContract,
    private val photoItem: PhotosItem
) :
    AndroidViewModel(application) {

    val inputTitle = MutableLiveData<String>()
    val inputAlbumId = MutableLiveData<String>()
    val inputThumbUrl = MutableLiveData<String>()
    val inputUrl = MutableLiveData<String>()

    private val _statusMessage = MutableLiveData<Event<String>>()
    val statusMessage: LiveData<Event<String>>
        get() = _statusMessage

    private val _statusSuccess = MutableLiveData<Event<String>>()
    val statusSuccess: LiveData<Event<String>>
        get() = _statusSuccess

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        inputTitle.value = photoItem.title
        inputAlbumId.value = photoItem.albumId.toString()
        inputThumbUrl.value = photoItem.thumbnailUrl
        inputUrl.value = photoItem.url
    }

    fun updateButton() {
        if (!verifyFieldsIsNotEmpty()) return

        coroutineScope.launch {
            val itemToUpdate = PhotosItem(
                photoItem.id,
                inputTitle.value.toString(),
                inputUrl.value.toString(),
                inputThumbUrl.value.toString(),
                Integer.parseInt(inputAlbumId.value.toString())
            )
            val putItem = repository.updateItem(itemToUpdate)
            try {
                val itemUpdated = putItem.await()
                _statusSuccess.value = Event("Item add successly! ID: ${itemUpdated.id.toString()}")
            } catch (e: Exception) {
                _statusMessage.value = Event("Ocurred an error in the add: ${e.printStackTrace()}")
            }
        }
    }

    private fun verifyFieldsIsNotEmpty(): Boolean {
        val hasMessageError = verifyFields(
            inputTitle.value.toString(),
            inputUrl.value.toString(),
            inputThumbUrl.value.toString(),
            inputAlbumId.value.toString()
        )

        return if (hasMessageError.isEmpty()) {
            true
        } else {
            _statusMessage.value = Event(hasMessageError)
            false
        }
    }

}