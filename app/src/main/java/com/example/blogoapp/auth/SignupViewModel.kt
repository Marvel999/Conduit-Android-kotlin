package com.example.blogoapp.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.ConduitClient
import com.example.api.model.entity.User
import com.example.api.model.entity.UserCred
import com.example.api.model.requests.UserRequests
import com.example.api.model.response.UserResponse
import com.example.blogoapp.data.UserAuthRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class SignupViewModel : ViewModel() {
    private val _userResponse=MutableLiveData<User>()
    val userResponse:LiveData<User>? =_userResponse

    fun addUser(userRequests: UserRequests){
        viewModelScope.launch {
            val user: Unit =UserAuthRepo.userSignup(userRequests).let {
                _userResponse.postValue(it.body()?.user)

            }
        }
    }
}