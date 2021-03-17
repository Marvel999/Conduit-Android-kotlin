package com.example.blogoapp.ui.personalfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogoapp.R
import com.example.blogoapp.adopter.GlobleFeedAdopter
import com.example.blogoapp.ui.article.ArticleViewModel

class PersonalFeedFragment : Fragment() {
    private lateinit var feedRecyclerView: RecyclerView
    private lateinit var globleFeedAdopter: GlobleFeedAdopter


    private lateinit var personalFeedViewModel: PersonalFeedViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        personalFeedViewModel =
                ViewModelProvider(this).get(PersonalFeedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_personalfeed, container, false)
        feedRecyclerView = root.findViewById(R.id.feedRecylerView)

        globleFeedAdopter=GlobleFeedAdopter()
        feedRecyclerView.layoutManager= LinearLayoutManager(context)
        feedRecyclerView.adapter=globleFeedAdopter
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personalFeedViewModel.personalFeed()
        personalFeedViewModel.articleResponse.observe({ lifecycle }) {
            globleFeedAdopter.submitList(it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}