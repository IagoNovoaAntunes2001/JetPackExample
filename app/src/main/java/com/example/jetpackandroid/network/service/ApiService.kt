package com.example.jetpackandroid.network.service

import com.example.jetpackandroid.network.model.PhotosItem
import com.example.jetpackandroid.network.model.ResponseDefault

import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ApiService {

    @GET("photos")
    fun getPhotosAsync():
            Deferred<List<PhotosItem>>

    @POST("photos")
    fun postPhotoAsync(@Body photosItem: PhotosItem):
            Deferred<PhotosItem>

    @PUT("photos/{id}")
    fun putPhotoAsync(@Body photosItem: PhotosItem, @Path("id") id: Int):
            Deferred<ResponseDefault>

    @DELETE("photos/{id}")
    fun deletePhotoAsync(@Path("id") id: Int)
            : Deferred<ResponseDefault>

}