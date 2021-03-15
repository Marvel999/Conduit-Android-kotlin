package com.example.api.service

import com.example.api.model.entity.UserCred
import com.example.api.model.entity.UserLoginRequest
import com.example.api.model.requests.LoginRequest
import com.example.api.model.requests.UserRequests
import com.example.api.model.response.ArticlesResponse
import com.example.api.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ConduitApi {



// add new user in database
    @POST("users")
    suspend fun addUser(
        @Body userRequests: UserRequests
    ):Response<UserResponse>


// login user in database
    @POST("users/login")
    suspend fun loginRequest(
        @Body loginRequest: LoginRequest
    ):Response<UserResponse>

// get articles
    @GET("articles")
    suspend fun getArticles(
        @Query("author")author:String?=null,
        @Query("tag")tag:List<String>?=null,
        @Query("favorited")favorited:String?=null
    ):Response<ArticlesResponse>
}