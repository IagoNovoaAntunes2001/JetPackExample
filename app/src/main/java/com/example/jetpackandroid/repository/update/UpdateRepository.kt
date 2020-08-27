package com.example.jetpackandroid.repository.update

import com.example.jetpackandroid.network.model.PhotosItem
import com.example.jetpackandroid.network.model.ResponseDefault
import com.example.jetpackandroid.network.service.ApiService
import kotlinx.coroutines.Deferred

class UpdateRepository(private val apiService: ApiService) : UpdateRepositoryContract {
    override fun updateItem(photoItem: PhotosItem): Deferred<ResponseDefault> {
        return apiService.putPhotoAsync(photoItem, photoItem.id)
    }
}