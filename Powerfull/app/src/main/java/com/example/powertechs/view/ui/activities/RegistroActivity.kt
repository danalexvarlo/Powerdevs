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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegistroActivity : AppCompatActivity()
{
    lateinit var buttonregistro : Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var nombre: EditText
    private lateinit var dbreference : DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbreference = database.reference.child("Usuario")
        setContentView(R.layout.activity_registro)
        buttonregistro=findViewById(R.id.buttonRegistro)
        val correo = findViewById<EditText>(R.id.correoRegistro)
        val contrasena = findViewById<EditText>(R.id.contrasenaRegistro)
        nombre = findViewById(R.id.nombreRegistro)
        buttonregistro.setOnClickListener {
            crearCuenta(correo.text.toString(), contrasena.text.toString())
        }
    }

    private fun crearCuenta(correo:String, contrasena: String)
    {
        val name: String = nombre.text.toString()
        firebaseAuth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this){
                Task -> if(Task.isSuccessful)
                {
                    val user = firebaseAuth.currentUser
                    val userdb = dbreference.child(user?.uid.toString())
                    userdb.child("name").setValue(name)
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
