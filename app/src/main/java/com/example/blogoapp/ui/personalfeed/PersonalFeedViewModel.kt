package com.example.blogoapp.ui.personalfeed

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.ConduitClient
import com.example.api.model.entity.Article
import com.example.api.model.response.ArticleResponse
import com.example.api.model.response.ArticlesResponse
import com.example.blogoapp.data.UserAuthRepo
import com.example.blogoapp.data.UserSharedpreferences
import kotlinx.coroutines.launch

class PersonalFeedViewModel : ViewModel() {

    private val _articleResponse = MutableLiveData<List<Article>>()
    val articleResponse: LiveData<List<Article>> = _articleResponse

    fun personalFeed(){
        viewModelScope.launch {
            UserAuthRepo.feedArticle().let {
                _articleResponse.postValue(it?.articles)
            }
        }
    }

    
}