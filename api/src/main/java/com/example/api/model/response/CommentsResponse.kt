package com.example.api.model.response


import com.example.api.model.entity.Comment
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentsResponse(
    @Json(name = "comments")
    val comments: List<Comment>
)