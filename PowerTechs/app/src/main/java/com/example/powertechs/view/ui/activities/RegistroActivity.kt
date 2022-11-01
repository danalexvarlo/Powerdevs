package com.example.powertechs.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.powertechs.R

class RegistroActivity : AppCompatActivity()
{
    lateinit var buttonregistro : Button
    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        buttonregistro=findViewById(R.id.buttonRegistro)
        buttonregistro.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
