package com.example.api.service

import com.example.api.model.ArticlesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConduitApi {
    @GET("articles")
    suspend fun getArticles(
        @Query("author")author:String?=null,
        @Query("tag")tag:List<String>?=null,
        @Query("favorited")favorited:String?=null
    ):Response<ArticlesResponse>
}