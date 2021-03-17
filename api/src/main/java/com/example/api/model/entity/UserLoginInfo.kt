package com.example.api.model.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserLoginInfo(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String
)