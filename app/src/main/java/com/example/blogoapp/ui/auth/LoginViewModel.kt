package com.example.blogoapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.model.entity.User
import com.example.blogoapp.data.UserAuthRepo
import com.example.blogoapp.data.UserSharedpreferences
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _userInfo=MutableLiveData<User?>()
    val userInfo:LiveData<User?> = _userInfo
   fun userLoginRequest(email: String, password:String){
       viewModelScope.launch{
           UserAuthRepo.userLogin(email,password)?.let {
               _userInfo.postValue(it)

           }
       }
   }
}