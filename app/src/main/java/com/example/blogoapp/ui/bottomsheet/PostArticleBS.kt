package com.example.blogoapp.ui.bottomsheet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blogoapp.R

class PostArticleBS : Fragment() {

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
        // TODO: Use the ViewModel
    }

}