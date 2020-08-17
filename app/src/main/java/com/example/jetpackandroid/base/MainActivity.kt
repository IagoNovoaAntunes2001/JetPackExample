package com.example.jetpackandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.jetpackandroid.R
import com.example.jetpackandroid.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navControlContext: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        configureActionBar()
    }

    private fun configureActionBar() {
        setSupportActionBar(myToolbar)

        navControlContext = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navControlContext.graph)

        setupActionBarWithNavController(navControlContext, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navControlContext.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
