package com.example.jetpackandroid.update

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackandroid.network.model.PhotosItem
import com.example.jetpackandroid.repository.update.UpdateRepositoryContract
import java.lang.IllegalArgumentException

class UpdateViewModelFactory(
    private val application: Application,
    private val repository: UpdateRepositoryContract,
    private val photosItem: PhotosItem
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateViewModel::class.java)) {
            return UpdateViewModel(application, repository, photosItem) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}