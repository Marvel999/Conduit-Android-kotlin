package com.example.blogoapp.ui.bottomsheet

import android.icu.text.CaseMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.ConduitClient
import com.example.api.model.entity.ArticleRequestEntity
import com.example.api.model.requests.CreateArticleRequest
import kotlinx.coroutines.launch
import retrofit2.http.Body

class PostArticleBottomSheetViewModel : ViewModel() {

    fun PostArticles(title: String,body: String,description:String,tagList:List<String>){
        viewModelScope.launch {
            val createArticleRequest=CreateArticleRequest(ArticleRequestEntity(
                title = title,
                body = body,
                description = description,
                tagList = tagList,
            ))
            ConduitClient.authApi.createArticles(createArticleRequest)
        }
    }
}