package com.example.api.service

import com.example.api.model.entity.Article
import com.example.api.model.requests.CreateArticleRequest
import com.example.api.model.requests.LoginRequest
import com.example.api.model.requests.UpdateProfileRequest
import com.example.api.model.requests.UserRequests
import com.example.api.model.response.ArticleResponse
import com.example.api.model.response.ArticlesResponse
import com.example.api.model.response.ProfileResponse
import com.example.api.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ConduitApi {



// add new user in database
    @POST("users")
    suspend fun addUser(
        @Body userRequests: UserRequests
    ):Response<UserResponse>


// add new user in database
    @POST("articles")
    suspend fun createArticles(
        @Body createArticleRequest: CreateArticleRequest
    ):Response<ArticleResponse>


// login user in database
@POST("users/login")
suspend fun loginUser(
    @Body userCreds: LoginRequest
): Response<UserResponse>

// get articles
    @GET("articles")
    suspend fun getArticles(
        @Query("author")author:String?=null,
        @Query("tag")tag:List<String>?=null,
        @Query("favorited")favorited:String?=null
    ):Response<ArticlesResponse>


    @GET("articles/feed")
    suspend fun getFeedArticles(): Response<ArticlesResponse>

    //get Profile
    @GET("profiles/{username}")
    suspend fun getProfile(
        @Path("username") username:String
    ):Response<ProfileResponse>

    //update Profile

    @PUT("user")
    suspend fun updateProfile(
   @Body updateProfileRequest: UpdateProfileRequest
    ):Response<UserResponse>

}