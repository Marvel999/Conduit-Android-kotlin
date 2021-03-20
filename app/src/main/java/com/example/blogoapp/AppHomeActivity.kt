package com.example.blogoapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.api.ConduitClient
import com.example.blogoapp.R
import com.example.blogoapp.data.UserSharedpreferences
import com.example.blogoapp.extensions.loadImage
import com.example.blogoapp.ui.ActivityViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AppHomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var activityViewModel:ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        activityViewModel= ViewModelProvider(this).get(ActivityViewModel::class.java)
        ConduitClient.authToken=UserSharedpreferences(this).token
        activityViewModel.getCurrentUserInfo()




        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        activityViewModel.userRes.observe({lifecycle}) {
            val hView = navView.getHeaderView(0)
            if (!it.user.image?.isEmpty()!!) {
                hView.findViewById<ImageView>(R.id.imageView)
                    .loadImage(
                        it.user.image!!,
                        true
                    )
            }
            if (!it.user.username.isEmpty()){
                hView.findViewById<TextView>(R.id.userName_nav)
                    .setText(
                        it.user.username,
                    )
            }
            if (!it.user.email.isEmpty()){
                hView.findViewById<TextView>(R.id.email_nav)
                    .setText(
                        it.user.email,
                    )
            }
        }
        //Todo: Set real user image
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_article, R.id.nav_personalfeed, R.id.nav_profile), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout ->{
                activityViewModel.logout(this).let {
                    if(it){
                        val intent= Intent(this@AppHomeActivity,AuthenticationActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }
            }

        }
        return false

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.app_home, menu)

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}