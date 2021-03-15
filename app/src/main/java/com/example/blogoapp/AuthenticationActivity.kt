package com.example.blogoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayout

class AuthenticationActivity : AppCompatActivity() {
    public lateinit var navController:NavController
    private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        tabLayout=findViewById(R.id.tabLayout)
        navController=Navigation.findNavController(findViewById(R.id.authFragmentNavHost));
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position){
                    0->{
                        navController.navigate(R.id.gotoLoginFragment)
                    }

                    1->{
                        navController.navigate(R.id.gotoSignupFragment)
                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })



    }
}