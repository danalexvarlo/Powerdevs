package com.example.powertechs.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.powertechs.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistroActivity : AppCompatActivity()
{
    lateinit var buttonregistro : Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
        setContentView(R.layout.activity_registro)
        buttonregistro=findViewById(R.id.buttonRegistro)
        val correo = findViewById<EditText>(R.id.correoRegistro)
        val contrasena = findViewById<EditText>(R.id.contrasenaRegistro)
        buttonregistro.setOnClickListener {
            crearCuenta(correo.text.toString(), contrasena.text.toString())
        }
    }

    private fun crearCuenta(correo:String, contrasena: String)
    {
        firebaseAuth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this){
                Task -> if(Task.isSuccessful){
                    Toast.makeText(baseContext, "Cuenta creada", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                else
                {
                    Toast.makeText(baseContext, "No se pudo crear la cuenta", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
