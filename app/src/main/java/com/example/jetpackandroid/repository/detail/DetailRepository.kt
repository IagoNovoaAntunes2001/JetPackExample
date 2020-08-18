package com.example.jetpackandroid.repository.detail

import com.example.jetpackandroid.network.model.ResponseDelete
import com.example.jetpackandroid.network.service.ApiService
import kotlinx.coroutines.Deferred

class DetailRepository(private val apiService: ApiService): DetailRepositoryContract {
    override fun deleteDetail(id: Int): Deferred<ResponseDelete> {
        return apiService.deletePhotoAsync(id)
    }
}