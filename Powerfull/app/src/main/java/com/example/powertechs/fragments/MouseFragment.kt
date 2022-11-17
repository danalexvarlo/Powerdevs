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

class MouseFragment: Fragment() {
    lateinit var boton : Button
    lateinit var cantidadMouse : EditText

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbreference : DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mouse, container, false)
        (activity as AppCompatActivity).supportActionBar?.title="Mouse"
        cantidadMouse = view.findViewById(R.id.cantidadMouse)

        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbreference = database.getReference("Carrito")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        boton = view.findViewById(R.id.botonAgregarMouse)
        boton.setOnClickListener()
        {
            agregarMouse()
        }
    }

    fun agregarMouse()
    {
        val user = firebaseAuth.currentUser
        val userdb = dbreference.child(user!!.uid).child("Mouse")
        userdb.child("titulo").setValue("Mouse")
        userdb.child("precio").setValue("$50.000")
        userdb.child("cantidad").setValue(cantidadMouse.text.toString())
        userdb.child("total").setValue(50000*cantidadMouse.text.toString().toInt())
        userdb.child("image").setValue("https://tauretcomputadores.com/images/products/Product_20220316141811726632324.png")
        findNavController().navigate(R.id.carritodecomprasFragment)
    }
}