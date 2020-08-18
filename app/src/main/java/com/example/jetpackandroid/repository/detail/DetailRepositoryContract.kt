package com.example.jetpackandroid.repository.detail

import com.example.jetpackandroid.network.model.ResponseDelete
import kotlinx.coroutines.Deferred

interface DetailRepositoryContract {
    fun deleteDetail(id: Int): Deferred<ResponseDelete>
}