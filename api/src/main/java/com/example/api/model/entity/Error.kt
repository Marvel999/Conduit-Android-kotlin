package com.example.api.model.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Error(
    @Json(name = "errors")
    val errors: Errors
)