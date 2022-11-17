package com.example.powertechs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.powertechs.R
import com.example.powertechs.view.ui.adapter.CarritoAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ComputadorFragment : Fragment() {

    lateinit var boton : Button
    lateinit var cantidadComputador : EditText

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbreference : DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_computador, container, false)
        (activity as AppCompatActivity).supportActionBar?.title="Computador"

        cantidadComputador = view.findViewById(R.id.cantidadComputador)

        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbreference = database.getReference("Carrito")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        boton = view.findViewById(R.id.botonAgregarPc)
        var cantidad: Int = cantidadComputador.text.toString().toInt()
        boton.setOnClickListener()
        {
            agregarComputador()
        }
    }

    fun agregarComputador()
    {
        val user = firebaseAuth.currentUser
        val userdb = dbreference.child(user!!.uid).child("Computador")
        userdb.child("titulo").setValue("Computador")
        userdb.child("precio").setValue("$3.550.000")
        userdb.child("cantidad").setValue(cantidadComputador.text.toString())
        userdb.child("total").setValue(3550000*cantidadComputador.text.toString().toInt())
        userdb.child("image").setValue("https://www.tauretcomputadores.com/images/products/Product_20210529124415515630695.png")
        findNavController().navigate(R.id.carritodecomprasFragment)
    }
}