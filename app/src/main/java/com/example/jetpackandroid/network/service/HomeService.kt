package com.example.jetpackandroid.network.service

object HomeApi {
    val retrofitService: HomeApiService by lazy {
        RetrofitInstance.getRetrofitInstance().create(HomeApiService::class.java)
    }
}