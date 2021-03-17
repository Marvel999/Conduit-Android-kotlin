package com.example.blogoapp.ui.article

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogoapp.R
import com.example.blogoapp.adopter.GlobleFeedAdopter

class ArticleFragment : Fragment() {

    private lateinit var feedRecyclerView: RecyclerView
   private lateinit var globleFeedAdopter: GlobleFeedAdopter
    private lateinit var articleViewModel: ArticleViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        articleViewModel =
                ViewModelProvider(this).get(ArticleViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_article, container, false)
        feedRecyclerView = root.findViewById(R.id.feedRecylerView)

        globleFeedAdopter=GlobleFeedAdopter()
        feedRecyclerView.layoutManager=LinearLayoutManager(context)
        feedRecyclerView.adapter=globleFeedAdopter

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val progressDialog = Dialog(requireContext())
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.setContentView(R.layout.custom_dialog_progress)

/* Custom setting to change TextView text,Color and Text Size according to your Preference*/

        val progressTv = progressDialog.findViewById(R.id.progress_tv) as TextView
        progressTv.text = resources.getString(R.string.loading)
        progressTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        progressTv.textSize = 19F

        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.setCancelable(false)
        progressDialog.show()



        articleViewModel.fetchGlobaleFeedArticle()
        articleViewModel.feed.observe({ lifecycle }) {
            globleFeedAdopter.submitList(it).let {
                progressDialog.dismiss()
            }
        }
    }
}