package com.example.powertechs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.powertechs.R
import com.example.powertechs.view.ui.adapter.CarritoAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class PantallaFragment: Fragment() {
    lateinit var boton : Button
    lateinit var cantidadPantalla : EditText

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbreference : DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pantalla, container, false)
        (activity as AppCompatActivity).supportActionBar?.title="Pantalla"
        cantidadPantalla = view.findViewById(R.id.cantidadPantalla)

        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbreference = database.getReference("Carrito")

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        boton = view.findViewById(R.id.botonAgregarPantalla)
        boton.setOnClickListener()
        {
            agregarPantalla()
        }
    }

    fun agregarPantalla()
    {
        val user = firebaseAuth.currentUser
        val userdb = dbreference.child(user!!.uid).child("Pantalla")
        userdb.child("titulo").setValue("Pantalla")
        userdb.child("precio").setValue("$599.000")
        userdb.child("cantidad").setValue(cantidadPantalla.text.toString())
        userdb.child("total").setValue(599000*cantidadPantalla.text.toString().toInt())
        userdb.child("image").setValue("https://tauretcomputadores.com/images/products/Product_202203301037051221337510.png")
        findNavController().navigate(R.id.carritodecomprasFragment)
    }

}