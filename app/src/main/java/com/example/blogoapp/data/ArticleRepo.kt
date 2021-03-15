package com.example.blogoapp.data

import com.example.api.ConduitClient
import com.example.api.model.requests.UserRequests

object ArticleRepo {

    val api=ConduitClient().api

   suspend fun getGlobleFeedArticle()=api.getArticles()

    suspend fun userSignup(userRequests: UserRequests)=api.addUser(userRequests)

}