package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.api.ApiHelper
import com.mindorks.retrofit.coroutines.data.repository.MainRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val totalTime = MutableLiveData<Long>().apply { 0 }

    fun startCaling() {
        val currentTime = System.currentTimeMillis()
        viewModelScope.launch {
            coroutineScope {
                val user = async { mainRepository.getUsers() }
                val posts = async { mainRepository.getPosts() }
                totalTime.value = System.currentTimeMillis() - currentTime
            }
        }

    }

    class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(MainRepository(apiHelper)) as T
            }
            throw IllegalArgumentException("Unknown class name")
        }

    }

}