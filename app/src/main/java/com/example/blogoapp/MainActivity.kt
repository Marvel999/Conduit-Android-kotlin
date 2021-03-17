package com.example.blogoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.blogoapp.data.UserSharedpreferences

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            if (UserSharedpreferences(this).token== null){
                              val intent = Intent(this@MainActivity,AuthenticationActivity::class.java)
                                startActivity(intent)
                                finish()
            }else{
                val intent = Intent(this@MainActivity,AppHomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        },2000)


    }
}