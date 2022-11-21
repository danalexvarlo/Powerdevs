package com.example.powertechs.fragments

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.powertechs.R
import com.example.powertechs.view.ui.adapter.CarritoAdapter
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class TarjetagraficaFragment: Fragment() {
    lateinit var boton : Button
    lateinit var cantidadTarjetagrafica : EditText

    lateinit var toggle : ActionBarDrawerToggle

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
        cantidadTarjetagrafica = view.findViewById(R.id.cantidadTarjetagrafica)

        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbreference = database.getReference("Carrito")

        val toolbar : Toolbar = view.findViewById(R.id.toolbar_tarjetgrafica)
        val drawerLayout: DrawerLayout = view.findViewById(R.id.fragmentTarjetagrafica)
        val navView : NavigationView = view.findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle( this.requireContext() as AppCompatActivity, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        toolbar.setTitle("Tarjeta Gráfica")

        toolbar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(Gravity.START)
            }
        }

        navView.setNavigationItemSelectedListener()
        {
            when(it.itemId)
            {
                R.id.nav_home -> {
                    findNavController().navigate(R.id.action_tarjetagraficaFragment_to_homeFragment)
                    Toast.makeText(context, "Página princial", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_perfil -> {
                    findNavController().navigate(R.id.action_tarjetgraficaFragment_to_editarmiperfilFragment)
                    Toast.makeText(context, "Tu perfil", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_productos -> {
                    findNavController().navigate(R.id.action_tarjetagrafica_to_productosFragment)
                    Toast.makeText(context, "Productos", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_carritodecompras ->{
                    findNavController().navigate(R.id.action_tarjetagraficaFragment_to_carritodecomprasFragment)
                    Toast.makeText(context, "Carrito de compras", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_comentarios ->{
                    findNavController().navigate(R.id.action_tarjetagrafica_to_comentariosFragment)
                    Toast.makeText(context, "Comentarios de nuestra tienda", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_contactenos ->{
                    findNavController().navigate(R.id.action_tarjetagrafica_to_mapaFragment)
                    Toast.makeText(context, "Contáctanos", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_cerrarsesion ->{
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_tarjetagraficaFragment_to_loginActivity)
                }

            }
            true
        }

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
        val userdb = dbreference.child(user!!.uid).child("Tarjetagrafica")
        userdb.child("titulo").setValue("Tarjeta Gráfica")
        userdb.child("precio").setValue("$5.050.000")
        userdb.child("cantidad").setValue(cantidadTarjetagrafica.text.toString())
        userdb.child("total").setValue(5050000*cantidadTarjetagrafica.text.toString().toInt())
        userdb.child("image").setValue("https://tauretcomputadores.com/images/products/Product_20220304145807935645986.png")
        findNavController().navigate(R.id.carritodecomprasFragment)
    }
}