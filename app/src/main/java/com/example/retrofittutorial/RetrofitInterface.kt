package com.example.retrofittutorial

import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("/comments")
    suspend fun getUserData(): Response<List<ApiResponse>>
}