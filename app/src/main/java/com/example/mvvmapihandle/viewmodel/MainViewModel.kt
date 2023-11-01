package com.example.mvvmapihandle.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvmapihandle.repository.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepo: MainRepo) :ViewModel(){
    val data = mainRepo.getPosts()
}