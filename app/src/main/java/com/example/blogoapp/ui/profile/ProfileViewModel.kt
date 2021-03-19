package com.example.blogoapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.ConduitClient
import com.example.api.model.entity.ArticleRequestEntity
import com.example.api.model.entity.UpdateUserProfileEntity
import com.example.api.model.requests.CreateArticleRequest
import com.example.api.model.requests.UpdateProfileRequest
import com.example.api.model.response.ArticleResponse
import com.example.api.model.response.UserResponse
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {


    private val _userResponse=MutableLiveData<UserResponse>()
    val userResponse: LiveData<UserResponse> =_userResponse

    fun PostArticles(userName: String,email: String,bio:String,image:String,password:String){
        viewModelScope.launch {
            val updateProfileRequest= UpdateProfileRequest(
                UpdateUserProfileEntity(
                    username = userName,
                    email = email,
                    bio = bio,
                    image = image,
                    password = password,

                )
            )
            ConduitClient.authApi.updateProfile(updateProfileRequest).let {
                _userResponse.postValue(it.body())
            }
        }
    }
}