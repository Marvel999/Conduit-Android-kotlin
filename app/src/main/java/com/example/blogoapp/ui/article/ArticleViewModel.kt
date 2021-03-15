package com.example.blogoapp.ui.article

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.model.entity.Article
import com.example.blogoapp.data.ArticleRepo
import kotlinx.coroutines.launch
import timber.log.Timber

class ArticleViewModel : ViewModel() {

    private val _feed=MutableLiveData<List<Article>>()
    val feed:LiveData<List<Article>> = _feed

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    fun fetchGlobaleFeedArticle(){
        viewModelScope.launch {
            ArticleRepo.getGlobleFeedArticle().body().let {
                _feed.postValue(it?.articles)
               Log.e("Article: ",it?.articlesCount.toString())
            }
        }
    }
    val text: LiveData<String> = _text
}