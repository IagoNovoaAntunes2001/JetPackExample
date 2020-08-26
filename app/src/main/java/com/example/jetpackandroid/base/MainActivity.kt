package com.example.jetpackandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.jetpackandroid.R
import com.example.jetpackandroid.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navControlContext: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        configureActiobBar()
    }

    private fun configureActiobBar() {
        setSupportActionBar(myToolbar)
        drawerLayout = binding.drawerLayout

        navControlContext = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navControlContext.graph)

        NavigationUI.setupActionBarWithNavController(this, navControlContext, drawerLayout)
        NavigationUI.setupWithNavController(binding.navigationView, navControlContext)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navControlContext,
            drawerLayout
        ) || super.onSupportNavigateUp()
    }
}
