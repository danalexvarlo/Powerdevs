package com.example.powertechs.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

class TarjetagraficaFragment: Fragment() {
    lateinit var boton : Button

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbreference : DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tarjetagrafica, container, false)
        (activity as AppCompatActivity).supportActionBar?.title="Productos"

        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbreference = database.getReference("Carrito")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        boton = view.findViewById(R.id.botonAgregarTarjetagrafica)
        boton.setOnClickListener()
        {
            agregarTarjetagrafica()
        }
    }

    fun agregarTarjetagrafica()
    {
        val user = firebaseAuth.currentUser
        val userdb = dbreference.child(user!!.uid).child("compra01")
        //userdb.addChildEventListener()
        userdb.child("titulo").setValue("Tarjeta Gráfica")
        userdb.child("precio").setValue("$"+5050000)
        userdb.child("image").setValue("https://tauretcomputadores.com/images/products/Product_20220304145807935645986.png")
        findNavController().navigate(R.id.carritodecomprasFragment)
    }
}