package com.example.blogoapp.data

import android.util.Log
import com.example.api.ConduitClient
import com.example.api.model.entity.User
import com.example.api.model.requests.LoginRequest
import com.example.api.model.entity.UserLoginInfo
import com.example.api.model.requests.UserRequests
import com.example.api.model.response.ArticlesResponse

object UserAuthRepo {
    val userApi=ConduitClient.api
    val authApi=ConduitClient.authApi
    suspend fun userSignup(userRequests: UserRequests): User? {
       val response=userApi.addUser(userRequests)
        ConduitClient.authToken=response.body()?.user?.token
        return response.body()?.user
    }
    suspend fun userLogin(email:String,password:String):User?{
        val response=userApi.loginUser(LoginRequest(
        UserLoginInfo(email,password)
    ))
        ConduitClient.authToken=response.body()?.user?.token
        return response.body()?.user
    }

    suspend fun feedArticle():ArticlesResponse?{
        Log.e("My Token","Token "+ConduitClient.authToken)
     val response=   authApi.getFeedArticles()

    return response.body()
    }


}