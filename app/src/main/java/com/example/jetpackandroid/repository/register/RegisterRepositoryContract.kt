package com.example.jetpackandroid.repository.register

import com.example.jetpackandroid.network.model.PhotosItem
import kotlinx.coroutines.Deferred

interface RegisterRepositoryContract {
    fun postItem(photosItem: PhotosItem): Deferred<PhotosItem>
}