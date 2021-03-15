package com.example.blogoapp.data

import com.example.api.ConduitClient

object ArticleRepo {

    val api=ConduitClient().api

   suspend fun getGlobleFeedArticle()=api.getArticles()

}