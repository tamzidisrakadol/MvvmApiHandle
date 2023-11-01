package com.example.mvvmapihandle.network

import com.example.mvvmapihandle.model.PostModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object{
        const val BaseUrl = "https://jsonplaceholder.typicode.com/"
    }

    @GET("posts")
    suspend fun getPost():Response<List<PostModel>>


}