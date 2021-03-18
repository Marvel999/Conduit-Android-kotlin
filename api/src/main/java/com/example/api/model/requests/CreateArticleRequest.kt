package com.example.api.model.requests


import com.example.api.model.entity.ArticleRequestEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateArticleRequest(
    @Json(name = "article")
    val articleRequestEntity: ArticleRequestEntity
)