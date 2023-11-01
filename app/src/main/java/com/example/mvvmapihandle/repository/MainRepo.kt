package com.example.mvvmapihandle.repository

import com.example.mvvmapihandle.network.ApiService
import com.example.mvvmapihandle.utils.result
import javax.inject.Inject

class MainRepo @Inject constructor(private val apiService: ApiService) {

    fun getPosts()= result {
        apiService.getPost()
    }
}