package com.example.blogoapp.ui.bottomsheet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.blogoapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UpdateUserInfo : BottomSheetDialogFragment() {
    companion object {
        fun newInstance() = PostArticleBS()
    }

    private lateinit var updateBtn:Button
    private lateinit var viewModel: PostArticleBottomSheetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_update_user_info, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateBtn=view.findViewById(R.id.updateBtn)
        updateBtn.background=resources.getDrawable(R.drawable.green_circuler_background)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostArticleBottomSheetViewModel::class.java)
//       viewModel.PostArticles(
//           body = "This is test article by jathalal",
//           title = "Jatalal Test Article",
//           description = "This article is for testing api from jathalal",
//           tagList = listOf("jatha","fun","test")
//       )
    }


}