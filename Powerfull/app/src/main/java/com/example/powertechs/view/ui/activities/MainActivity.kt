package com.example.powertechs.view.ui.activities

import android.content.Intent
import android.content.res.Configuration
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.powertechs.R
import com.example.powertechs.databinding.ActivityMainBinding
import com.example.powertechs.view.ui.activities.LoginActivity
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity()
{

    lateinit var binding: ActivityMainBinding
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        /*requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()*/
        supportActionBar?.hide()

        sleep(2000)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.animationView.setAnimation(R.raw.powerfull)
        binding.animationView.playAnimation()

        handler = Handler(Looper.myLooper()!!)
        handler.postDelayed(
            {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 6000)
    }
}
