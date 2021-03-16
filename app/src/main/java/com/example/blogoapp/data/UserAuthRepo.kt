package com.example.blogoapp.data

import com.example.api.ConduitClient
import com.example.api.model.entity.UserLoginRequest
import com.example.api.model.requests.LoginRequest
import com.example.api.model.requests.UserRequests

object UserAuthRepo {
    val userAuthApi=ConduitClient().api
    suspend fun userSignup(userRequests: UserRequests)= userAuthApi.addUser(userRequests)
    suspend fun userLogin(email:String,password:String)= userAuthApi.loginRequest(LoginRequest(
        UserLoginRequest(email,password)
    ))
}