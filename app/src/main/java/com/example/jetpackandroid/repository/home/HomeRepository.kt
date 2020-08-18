package com.example.jetpackandroid.repository.home

import com.example.jetpackandroid.network.model.PhotosItem
import com.example.jetpackandroid.network.service.ApiService
import kotlinx.coroutines.Deferred

class HomeRepository(private val apiService: ApiService) :
    HomeRepositoryContract {

    override fun getPhotos(): Deferred<List<PhotosItem>> {
        return apiService.getPhotosAsync()
    }

}