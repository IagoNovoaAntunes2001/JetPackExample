package com.example.jetpackandroid.network.service

import com.example.jetpackandroid.network.model.PhotosItem
import com.example.jetpackandroid.network.model.ResponseDelete
import kotlinx.coroutines.Deferred
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("photos")
    fun getPhotosAsync():
            Deferred<List<PhotosItem>>

    @DELETE("photos/{id}")
    fun deletePhotoAsync(@Path("id") id: Int)
            : Deferred<ResponseDelete>

}