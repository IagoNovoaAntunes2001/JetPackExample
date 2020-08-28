package com.example.jetpackandroid.repository.register

import com.example.jetpackandroid.network.model.PhotosItem
import com.example.jetpackandroid.network.service.ApiService
import kotlinx.coroutines.Deferred

class RegisterRepository(private val apiService: ApiService) : RegisterRepositoryContract {
    override fun postItem(photosItem: PhotosItem): Deferred<PhotosItem> {
        return apiService.postPhotoAsync(photosItem)
    }
}
