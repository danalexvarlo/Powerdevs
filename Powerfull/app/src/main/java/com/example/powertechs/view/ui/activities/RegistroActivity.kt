package com.example.powertechs.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
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
    //lateinit var toolbar: Toolbar
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var nombre: EditText
    private lateinit var apellidos : EditText
    private lateinit var email: EditText
    private lateinit var contacto: EditText
    private lateinit var password: EditText
    private lateinit var dbreference : DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val toolbar: Toolbar = findViewById(R.id.registroToolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setTitle("Registro")
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbreference = database.reference.child("Usuario")

        buttonregistro=findViewById(R.id.buttonRegistro)
        val correo = findViewById<EditText>(R.id.correoRegistro)
        val contrasena = findViewById<EditText>(R.id.contrasenaRegistro)
        nombre = findViewById(R.id.nombreRegistro)
        apellidos = findViewById(R.id.apellidoRegistro)
        email = findViewById(R.id.correoRegistro)
        contacto = findViewById(R.id.celularRegistro)
        password = findViewById(R.id.contrasenaRegistro)

        buttonregistro.setOnClickListener {
            crearCuenta(correo.text.toString(), contrasena.text.toString())
        }
    }

    private fun crearCuenta(correo:String, contrasena: String)
    {
        val name: String = nombre.text.toString()
        val emails: String = email.text.toString()
        val passw: String = password.text.toString()
        val lastName: String = apellidos.text.toString()
        val contact: String = contacto.text.toString()
        firebaseAuth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this){
                Task -> if(Task.isSuccessful)
                {
                    val user = firebaseAuth.currentUser
                    val userdb = dbreference.child(user?.uid.toString())
                    userdb.child("name").setValue(name)
                    userdb.child("last name").setValue(lastName)
                    userdb.child("correo").setValue(emails)
                    userdb.child("contacto").setValue(contact)
                    userdb.child("contrasena").setValue(passw)
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
