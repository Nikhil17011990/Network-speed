package com.mindorks.retrofit.coroutines.data.repository

import com.example.myapplication.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
    suspend fun getPosts() = apiHelper.getPosts()
}