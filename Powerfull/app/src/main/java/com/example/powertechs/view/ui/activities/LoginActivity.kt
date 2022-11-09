package com.example.powertechs.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.powertechs.R
import com.example.powertechs.fragments.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity()
{
    lateinit var registrobutton: TextView
    lateinit var iniciobutton:Button
    lateinit var recuperarbutton : TextView
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = Firebase.auth

        val correo = findViewById<EditText>(R.id.loginCorreo)
        val contrasena = findViewById<EditText>(R.id.loginContrasena)

        iniciobutton= findViewById(R.id.Binicio)
        registrobutton= findViewById(R.id.Bregistro)
        recuperarbutton= findViewById(R.id.Brecuperar)

        registrobutton.setOnClickListener{
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        iniciobutton.setOnClickListener{
            login(correo.text.toString(), contrasena.text.toString())
        }

        recuperarbutton.setOnClickListener{
            startActivity(Intent(this, RecuperarActivity::class.java))
        }
    }

    private fun login(correo: String, contrasena: String)
    {
        firebaseAuth.signInWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this) {
                Task ->if(Task.isSuccessful)
                {
                    val user = firebaseAuth.currentUser
                Toast.makeText(baseContext, user?.uid.toString(), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                }
                else
                {
                    Toast.makeText(baseContext, "Error de autenticación", Toast.LENGTH_SHORT).show()
                }
            }
    }
}