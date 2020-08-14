package com.example.jetpackandroid.network.service

import com.example.jetpackandroid.network.model.PhotosItem
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface HomeApiService {
    @GET("photos")
    fun getPhotos():
            Deferred<List<PhotosItem>>
}