package com.example.api.model.requests


import com.example.api.model.entity.UpdateUserProfileEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateProfileRequest(
    @Json(name = "user")
    val updateUserProfileEntity: UpdateUserProfileEntity
)