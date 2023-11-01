package com.example.mvvmapihandle.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> result(call:suspend ()->Response<T>):Flow<ApiResponse<T?>> = flow {

    emit(ApiResponse.Loading)
    try {
        val c = call()
        c.let {
            if (c.isSuccessful){
                emit(ApiResponse.Success(it.body()))
            }else{
                c.errorBody()?.let {
                    it.close()
                    emit(ApiResponse.Failure(it.toString()))
                }
            }
        }
    }catch (e:Exception){
        e.printStackTrace()
        emit(ApiResponse.Failure(e.message.toString()))
    }
}