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
import com.example.powertechs.view.ui.adapter.ProductosAdapter
import com.google.android.gms.tasks.Task
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class TecladoFragment: Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbreference : DatabaseReference
    private lateinit var database: FirebaseDatabase

    lateinit var toggle : ActionBarDrawerToggle

    lateinit var boton : Button
    lateinit var cantidadTeclado : EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_teclado, container, false)
        cantidadTeclado = view.findViewById(R.id.cantidadTeclado)

        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbreference = database.reference.child("Carrito")

        val toolbar : Toolbar = view.findViewById(R.id.toolbar_teclado)
        val drawerLayout: DrawerLayout = view.findViewById(R.id.fragment_teclado)
        val navView : NavigationView = view.findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle( this.requireContext() as AppCompatActivity, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        toolbar.setTitle("Teclado")

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
                    findNavController().navigate(R.id.action_tecladoFragment_to_homeFragment)
                    Toast.makeText(context, "Página princial", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_perfil -> {
                    findNavController().navigate(R.id.action_tecladoFragment_to_editarmiperfilFragment)
                    Toast.makeText(context, "Tu perfil", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_productos -> {
                    findNavController().navigate(R.id.action_tecladoFragment_to_productosFragment)
                    Toast.makeText(context, "Productos", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_carritodecompras ->{
                    findNavController().navigate(R.id.action_tecladoFragment_to_carritodecomprasFragment)
                    Toast.makeText(context, "Carrito de compras", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_comentarios ->{
                    findNavController().navigate(R.id.action_tecladoFragment_to_comentariosFragment)
                    Toast.makeText(context, "Comentarios de nuestra tienda", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_contactenos ->{
                    findNavController().navigate(R.id.action_tecladoFragment_to_mapaFragment)
                    Toast.makeText(context, "Contáctanos", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_cerrarsesion ->{
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_tecladoFragment_to_loginActivity)
                }

            }
            true
        }

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