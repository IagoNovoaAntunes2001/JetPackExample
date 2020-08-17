package com.example.jetpackandroid.network.service

class HomeRepository(private val homeApiService: HomeApiService) {

    val photos = homeApiService.getPhotos()

}