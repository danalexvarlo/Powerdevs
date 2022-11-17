package com.example.powertechs.fragments

import android.os.Bundle
import android.util.Log
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
import com.example.powertechs.view.ui.adapter.ProductosAdapter
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class TecladoFragment: Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbreference : DatabaseReference
    private lateinit var database: FirebaseDatabase

    lateinit var boton : Button
    lateinit var cantidadTeclado : EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_teclado, container, false)
        (activity as AppCompatActivity).supportActionBar?.title="Teclado"
        cantidadTeclado = view.findViewById(R.id.cantidadTeclado)

        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbreference = database.reference.child("Carrito")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        boton = view.findViewById(R.id.botonAgregarTeclado)
        boton.setOnClickListener()
        {
            agregarCarrito()
        }
    }

    private fun agregarCarrito()
    {
        val user = firebaseAuth.currentUser
        val userdb = dbreference.child(user!!.uid).child("Teclado")
        userdb.child("titulo").setValue("Teclado")
        userdb.child("cantidad").setValue(cantidadTeclado.text.toString())
        userdb.child("total").setValue(120000*cantidadTeclado.text.toString().toInt())
        userdb.child("precio").setValue("$120.000")
        userdb.child("image").setValue("https://tauretcomputadores.com/images/products/Product_202204251701031426079895.png")
        findNavController().navigate(R.id.carritodecomprasFragment)
    }
}