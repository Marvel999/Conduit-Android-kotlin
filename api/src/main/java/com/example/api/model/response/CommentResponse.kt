package com.example.api.model.response


import com.example.api.model.entity.Comment
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentResponse(
    @Json(name = "comment")
    val comment: Comment
)