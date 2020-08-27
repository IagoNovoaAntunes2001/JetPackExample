package com.example.jetpackandroid.repository.update

import com.example.jetpackandroid.network.model.PhotosItem
import com.example.jetpackandroid.network.model.ResponseDefault
import kotlinx.coroutines.Deferred

interface UpdateRepositoryContract {
    fun updateItem(photoItem: PhotosItem): Deferred<ResponseDefault>
}