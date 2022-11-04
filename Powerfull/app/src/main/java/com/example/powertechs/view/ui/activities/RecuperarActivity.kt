package com.example.powertechs.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.powertechs.R

class RecuperarActivity : AppCompatActivity()
{
    lateinit var restaurarbutton : Button
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar)
        restaurarbutton = findViewById(R.id.Brestaurar)
        restaurarbutton.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}