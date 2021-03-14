package com.example.blogoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.api.ConduitClient
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      val conduitClient=ConduitClient()
        runBlocking {
            val articels=conduitClient.api.getArticles()
            Log.e("Article",""+articels.body()?.articles.toString())
        }


    }
}