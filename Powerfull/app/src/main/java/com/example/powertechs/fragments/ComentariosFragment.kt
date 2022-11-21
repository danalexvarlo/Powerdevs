package com.example.powertechs.fragments

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ActionBarContainer
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.powertechs.R
import com.example.powertechs.view.ui.adapter.ComentariosAdapter
import com.example.powertechs.view.ui.viewmodel.ComentariosViewModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ComentariosFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ComentariosAdapter
    lateinit var toggle : ActionBarDrawerToggle

    private lateinit var firebaseAuth: FirebaseAuth

    private val viewmodel by lazy { ViewModelProvider(this).get(ComentariosViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_comentarios, container, false)

        firebaseAuth = Firebase.auth

        val toolbar : Toolbar = view.findViewById(R.id.toolbar_comentarios)
        val drawerLayout: DrawerLayout = view.findViewById(R.id.comentarios_fragment)
        val navView : NavigationView = view.findViewById(R.id.nav_view)

        recyclerView = view.findViewById(R.id.recyclerview_comentarios)
        adapter = ComentariosAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        observeData()

        toggle = ActionBarDrawerToggle( this.requireContext() as AppCompatActivity, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        toolbar.setTitle("Calificación Powerfull")

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
                    findNavController().navigate(R.id.action_comentariosFragment_to_homeFragment)
                    Toast.makeText(context, "Página princial", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_perfil -> {
                    findNavController().navigate(R.id.action_comentariosFragment_to_perfilFragment)
                    Toast.makeText(context, "Perfil", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_productos -> {
                    findNavController().navigate(R.id.action_comentariosFragment_to_productosFragment)
                    Toast.makeText(context, "Productos", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_carritodecompras ->{
                    findNavController().navigate(R.id.action_comentariosFragment_to_carritodecomprasFragment)
                    Toast.makeText(context, "Carrito de compras", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_comentarios ->{
                    findNavController().navigate(R.id.comentariosFragment)
                    Toast.makeText(context, "Comentarios de nuestra tienda", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_contactenos ->{
                    findNavController().navigate(R.id.action_comentariosFragment_to_mapaFragment)
                    Toast.makeText(context, "Contáctanos", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_cerrarsesion ->{
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_comentariosFragment_to_loginActivity)
                }
            }
            true
        }

        return view
    }



    fun observeData()
    {
        viewmodel.comentariosData().observe(viewLifecycleOwner, Observer
        {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
        {
            return true
        }
        else
        {
            return super.onOptionsItemSelected(item)
        }
    }
}