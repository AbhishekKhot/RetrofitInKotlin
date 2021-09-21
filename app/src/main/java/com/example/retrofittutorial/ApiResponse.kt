package com.example.retrofittutorial

data class ApiResponse(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)