package com.example.myapplication.data.api


class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()
    suspend fun getPosts() = apiService.getPosts()
}