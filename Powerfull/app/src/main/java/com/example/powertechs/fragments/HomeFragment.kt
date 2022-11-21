package com.example.powertechs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.example.powertechs.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        firebaseAuth = Firebase.auth

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        val cardProductos = view.findViewById<ImageView>(R.id.cardProductos)
        cardProductos.setOnClickListener()
        {
            findNavController().navigate(R.id.action_homeFragment_to_productosFragment)
        }

        val cardCarrito = view.findViewById<ImageView>(R.id.cardCarrito)
        cardCarrito.setOnClickListener()
        {
            findNavController().navigate(R.id.action_homeFragment_to_carritodecomprasFragment)
        }

        val cardEditarperfil = view.findViewById<ImageView>(R.id.cardEditarperfil)
        cardEditarperfil.setOnClickListener()
        {
            findNavController().navigate(R.id.action_homeFragment_to_editarmiperfilFragment)
        }

        val cardContactenos = view.findViewById<ImageView>(R.id.cardContactenos)
        cardContactenos.setOnClickListener()
        {
            findNavController().navigate(R.id.action_homeFragment_to_mapaFragment)
        }

        val cardCerrarsesion = view.findViewById<ImageView>(R.id.cerrarSesion)
        cardCerrarsesion.setOnClickListener()
        {
            firebaseAuth.signOut()
            findNavController().navigate(R.id.action_homeFragment_to_loginActivity)
            true
        }

        val cardComentarios = view.findViewById<ImageView>(R.id.cardComentarios)
        cardComentarios.setOnClickListener()
        {
            findNavController().navigate(R.id.action_homeFragment_to_comentariosFragment)
        }
    }
}
