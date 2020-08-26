package com.example.jetpackandroid.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackandroid.repository.register.RegisterRepositoryContract
import java.lang.IllegalArgumentException

class RegisterViewModelFactory(private val application: Application, private val repository: RegisterRepositoryContract): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(application, repository) as T
        }
        throw IllegalArgumentException("")
    }
}