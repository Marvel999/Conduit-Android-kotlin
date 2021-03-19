package com.example.blogoapp.ui.bottomsheet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.api.ConduitClient
import com.example.api.model.response.ArticleResponse
import com.example.blogoapp.R
import com.example.blogoapp.data.UserSharedpreferences
import com.example.blogoapp.ui.article.ArticleViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PostArticleBS : BottomSheetDialogFragment() {
    companion object {
        fun newInstance() = PostArticleBS()
    }

    private lateinit var updateBtn:Button
    private lateinit var edTitle:EditText
    private lateinit var edShortDescription:EditText
    private lateinit var edArticleId:EditText
    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_article_b_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateBtn=view.findViewById(R.id.updateBtn)
        edTitle=view.findViewById(R.id.edTitle)
        edShortDescription=view.findViewById(R.id.edShortDescription)
        edArticleId=view.findViewById(R.id.edArticleId)
        updateBtn.background=resources.getDrawable(R.drawable.green_circuler_background)
        ConduitClient.authToken=UserSharedpreferences(requireContext()).token
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ArticleViewModel::class.java)

        updateBtn.setOnClickListener {
            if (checkValidation()){
                viewModel.PostArticles(
           body =edArticleId.text.toString(),
           title = edTitle.text.toString(),
           description =  edShortDescription.text.toString(),
           tagList = listOf("BlogoApp")
       ).let {
                    Toast.makeText(requireContext(),"Please Wait...",Toast.LENGTH_LONG).show()

                }
            }
        }

        viewModel.articleResponse.observe({lifecycle}){
            when(it){
                is ArticleResponse->{
                    Toast.makeText(requireContext(),"Article is successful",Toast.LENGTH_LONG).show()
                    viewModel.fetchGlobaleFeedArticle()
                    dismiss()
                }
                else->{
                    Toast.makeText(requireContext(),"Sorry Some Technical Issue",Toast.LENGTH_LONG).show()
                    dismiss()
                }
            }
        }
    }

    fun checkValidation():Boolean{
        if (edTitle.text.isEmpty())
            return false
        else if (edShortDescription.text.isEmpty())
            return false
        else return !edArticleId.text.isEmpty()
    }


}