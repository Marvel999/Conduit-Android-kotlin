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
import com.example.api.model.response.ProfileResponse
import com.example.api.model.response.UserResponse
import com.example.blogoapp.data.UserAuthRepo
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {


    private val _userResponse=MutableLiveData<UserResponse>()
    val userResponse: LiveData<UserResponse> =_userResponse
 private val _profileResponse=MutableLiveData<ProfileResponse>()
    val profileResponse: LiveData<ProfileResponse> =_profileResponse
    private val _userRes=MutableLiveData<UserResponse>()
    val userRes: LiveData<UserResponse> =_userRes

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

    fun getProfileInfo(userName:String){
        viewModelScope.launch {
            UserAuthRepo.getUserProfile(userName).let {
                _profileResponse.postValue(it.body())
            }
        }
    }

    fun getCurrentUserInfo(){
        viewModelScope.launch {
            UserAuthRepo.getCurrentUser().let {
                _userRes.postValue(it.body())
            }
        }
    }
}