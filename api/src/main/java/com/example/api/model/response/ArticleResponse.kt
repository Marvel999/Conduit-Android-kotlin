package com.example.api.model.response


import com.example.api.model.entity.Article
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleResponse(
    @Json(name = "article")
    val article: Article
)