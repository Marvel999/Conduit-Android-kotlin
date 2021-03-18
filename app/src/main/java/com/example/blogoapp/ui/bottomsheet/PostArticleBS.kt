package com.example.blogoapp.ui.bottomsheet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blogoapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PostArticleBS : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = PostArticleBS()
    }

    private lateinit var viewModel: PostArticleBottomSheetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_article_b_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostArticleBottomSheetViewModel::class.java)
       viewModel.PostArticles(
           body = "This is test article by jathalal",
           title = "Jatalal Test Article",
           description = "This article is for testing api from jathalal",
           tagList = listOf("jatha","fun","test")
       )
    }

}