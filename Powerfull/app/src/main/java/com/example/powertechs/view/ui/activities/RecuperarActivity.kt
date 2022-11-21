package com.example.powertechs.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.powertechs.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecuperarActivity : AppCompatActivity()
{
    lateinit var restaurarbutton : Button
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar)

        val toolbar: Toolbar = findViewById(R.id.toolbar_recuperar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setTitle("Recuperar contraseña")
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val correo = findViewById<EditText>(R.id.recuperarCorreo)
        firebaseAuth = Firebase.auth
        restaurarbutton = findViewById(R.id.Brestaurar)
        restaurarbutton.setOnClickListener{
            cambioContrasena(correo.text.toString())
        }
    }

    private fun cambioContrasena(correo: String)
    {
        firebaseAuth.sendPasswordResetEmail(correo)
            .addOnCompleteListener(this) { Task ->
                if (Task.isSuccessful)
                {
                    Toast.makeText(baseContext, "Se envió la información a su correo", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                }

                else
                {
                    Toast.makeText(baseContext, "Error, correo no existente", Toast.LENGTH_SHORT).show()
                }
            }
    }
}