package com.example.jetpackandroid.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackandroid.network.model.PhotosItem
import com.example.jetpackandroid.repository.register.RegisterRepositoryContract
import com.example.jetpackandroid.utils.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel(
    application: Application,
    private val repository: RegisterRepositoryContract
) : AndroidViewModel(application) {

    val inputTitle = MutableLiveData<String>()
    val inputAlbumId = MutableLiveData<String>()
    val inputThumbUrl = MutableLiveData<String>()
    val inputUrl = MutableLiveData<String>()

    private val _statusSuccess = MutableLiveData<Event<String>>()
    val statusSuccess: LiveData<Event<String>>
        get() = _statusSuccess

    private val _statusMessage = MutableLiveData<Event<String>>()
    val statusMessage: LiveData<Event<String>>
        get() = _statusMessage

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun postButton() {
        if (!verifyFieldsIsEmpty()) return

        coroutineScope.launch {
            val itemToSend = PhotosItem(
                0,
                inputTitle.value.toString(),
                inputUrl.value.toString(),
                inputThumbUrl.value.toString(),
                Integer.parseInt(inputAlbumId.value.toString())
            )
            val postItem = repository.postItem(itemToSend)
            try {
                val itemSended = postItem.await()
                _statusSuccess.value = Event("Item add successly! ID: ${itemSended.id.toString()}")
            } catch (e: Exception) {
                _statusMessage.value = Event("Ocurred an error in the add: ${e.printStackTrace()}")
            }
        }
    }

    private fun verifyFieldsIsEmpty(): Boolean {
        return isFieldsEmpety()
    }

    private fun isFieldsEmpety(): Boolean {
        if (inputTitle.value == null || inputTitle.value.toString().isEmpty()) {
            _statusMessage.value = Event("Please enter title's name")
            return false
        } else if (inputUrl.value == null || inputUrl.value.toString().isEmpty()) {
            _statusMessage.value = Event("Please enter url's")
            return false
        } else if (inputThumbUrl.value == null || inputThumbUrl.toString().isEmpty()) {
            _statusMessage.value = Event("Please enter thumb's url")
            return false
        } else if (inputAlbumId.value == null || inputAlbumId.value.toString().isEmpty()) {
            _statusMessage.value = Event("Please enter album's id")
            return false
        } else if (isaNumber(inputAlbumId.value.toString())) {
            _statusMessage.value = Event("Please enter with a number, not a string!")
            return false
        }
        return true
    }

    private fun isaNumber(number: String): Boolean {
        if (number.contains("^[a-Z]")) {
            return true
        }
        return false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}