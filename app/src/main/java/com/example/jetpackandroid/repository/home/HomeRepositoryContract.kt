package com.example.jetpackandroid.repository.home

import com.example.jetpackandroid.network.model.PhotosItem
import kotlinx.coroutines.Deferred

interface HomeRepositoryContract {

    fun getPhotos(): Deferred<List<PhotosItem>>

}