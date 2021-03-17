package com.example.blogoapp.adopter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api.model.entity.Article
import com.example.api.model.entity.Author
import com.example.blogoapp.R
import com.example.blogoapp.extensions.loadImage
import java.util.zip.Inflater

class GlobleFeedAdopter:ListAdapter<Article,GlobleFeedAdopter.ArticleViewHolder>(
    object :DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.toString()==oldItem.toString()
        }

    }
) {


    inner class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val  tvAuthor:TextView=itemView.findViewById(R.id.tvAuthorName)
        val  tvArticleTitle: TextView=itemView.findViewById(R.id.tvArticleTitle)
        val  tvArticleDescription: TextView=itemView.findViewById(R.id.tvArticleDescription)
        val  tvDate: TextView=itemView.findViewById(R.id.tvDate)
        val  imgAvtar: ImageView=itemView.findViewById(R.id.imgAvtar)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.globle_feed_list_item_article, parent, false)
        val root=ArticleViewHolder(
            view
        )

       return root
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article=getItem(position)


       holder.tvAuthor.text=article.author.username
        holder.tvDate.text="25 Dec, 2021"
        holder.tvArticleTitle.text=article.title
        holder.tvArticleDescription.text=article.description
        holder.imgAvtar.loadImage(article.author.image,true)


    }
}