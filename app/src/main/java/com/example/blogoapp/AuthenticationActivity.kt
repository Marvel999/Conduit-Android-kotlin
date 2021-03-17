package com.example.blogoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.api.model.entity.User
import com.example.blogoapp.auth.SignupViewModel
import com.google.android.material.tabs.TabLayout

class AuthenticationActivity : AppCompatActivity() {
    public lateinit var navController:NavController
    public lateinit var tabLayout: TabLayout
    private lateinit var viewModel: SignupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        viewModel= ViewModelProvider(this).get(SignupViewModel::class.java)

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
        val tab=tabLayout.getTabAt(0)
        viewModel.userResponse?.observe({lifecycle}){
            when(it){
                is User ->{
                    tab?.select()
                    Toast.makeText(this,"tab swithcing in android",Toast.LENGTH_LONG).show()
                }
            }
        }




    }
}