package com.example.powertechs.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.powertechs.R
import com.example.powertechs.fragments.HomeFragment

class HomeActivity : AppCompatActivity() 
{
    override fun onCreate(savedInstanceState: Bundle?) 
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //replaceFragment(HomeFragment())
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }

    /*fun replaceFragment(HomeFragment: Fragment)
    {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.nav_graph, HomeFragment)
        transaction.addToBackStack(null)
        //transaction.commit()
    }*/
}