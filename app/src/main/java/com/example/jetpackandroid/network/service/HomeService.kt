package com.example.jetpackandroid.network.service

object HomeApi {
    val RETROFIT_SERVICE: ApiService by lazy {
        RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
    }
}