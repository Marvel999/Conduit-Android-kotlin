package com.example.blogoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.api.ConduitClient
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
                              val intent = Intent(this@MainActivity,AuthenticationActivity::class.java)
                                startActivity(intent)
                                finish()
        },2000)


    }
}