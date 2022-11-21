package com.example.powertechs.fragments

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.example.powertechs.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class DejarcomentarioFragment : Fragment()
{
    // Inicialización de variables

    // Variables de la base de datos
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbreference : DatabaseReference
    private lateinit var dbUsuario: DatabaseReference
    private lateinit var database: FirebaseDatabase

    // Variables de los layouts
    lateinit var nombreUsuario : TextView
    lateinit var comentario: EditText
    lateinit var dejarComentario: Button
    lateinit var oneStar: ImageButton
    lateinit var twoStars: ImageButton
    lateinit var threeStars: ImageButton
    lateinit var fourStars: ImageButton
    lateinit var fiveStars: ImageButton

    // Variable para vista del menú desplegable a la izquierda
    lateinit var toggle : ActionBarDrawerToggle


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dejarcomentario, container, false)

        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()

        dbreference = database.reference.child("Comentarios")
        dbUsuario = database.reference.child("Usuario")

        val toolbar : Toolbar = view.findViewById(R.id.toolbar_dejarcomentario)
        val drawerLayout: DrawerLayout = view.findViewById(R.id.dejarcomentario_fragment)
        val navView : NavigationView = view.findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle( this.requireContext() as AppCompatActivity, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        toolbar.setTitle("Dejar calificación")

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
                    findNavController().navigate(R.id.action_dejarcomentarioFragment_to_homeFragment)
                    Toast.makeText(context, "Página princial", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_perfil -> {
                    findNavController().navigate(R.id.action_dejarcomentarioFragment_to_perfilFragment)
                    Toast.makeText(context, "Tu perfil", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_productos -> {
                    findNavController().navigate(R.id.action_dejarcomentarioFragment_to_productosFragment)
                    Toast.makeText(context, "Productos", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_comentarios -> {
                    findNavController().navigate(R.id.action_dejarcomentarioFragment_to_comentariosFragment)
                    Toast.makeText(context, "Comentarios de la tienda", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_carritodecompras ->{
                    findNavController().navigate(R.id.action_dejarcomentarioFragment_to_carritodecomprasFragment)
                    Toast.makeText(context, "Carrito de compras", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_contactenos ->{
                    findNavController().navigate(R.id.action_dejarcomentarioFragment_to_mapaFragment)
                    Toast.makeText(context, "Contáctanos", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_cerrarsesion ->{
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_dejarcomentarioFragment_to_loginActivity)
                }
            }
            true
        }


        nombreUsuario = view.findViewById(R.id.nombreDejarcomentario)
        comentario = view.findViewById(R.id.dejarComentarioEdittext)
        dejarComentario = view.findViewById(R.id.dejarComentarioButton)
        oneStar = view.findViewById(R.id.oneStar)
        twoStars = view.findViewById(R.id.twoStars)
        threeStars = view.findViewById(R.id.threeStars)
        fourStars = view.findViewById(R.id.fourStars)
        fiveStars = view.findViewById(R.id.fiveStars)

        oneStar.setOnClickListener()
        {
            val rate = "https://firebasestorage.googleapis.com/v0/b/powerfull-6c0bc.appspot.com/o/unaEstrella.png?alt=media&token=3aa7299b-82be-4ce4-8a5b-4118e966df9b"
            calificacion(rate)
        }

        twoStars.setOnClickListener()
        {
            val rate = "https://firebasestorage.googleapis.com/v0/b/powerfull-6c0bc.appspot.com/o/dosEstrellas.png?alt=media&token=e1a25872-500e-4d6c-acf9-c910beb424b9"
            calificacion(rate)
        }

        threeStars.setOnClickListener()
        {
            val rate = "https://firebasestorage.googleapis.com/v0/b/powerfull-6c0bc.appspot.com/o/tresEstrellas.png?alt=media&token=40a32947-2a7d-481e-80e2-1f539e3e0b48"
            calificacion(rate)
        }

        fourStars.setOnClickListener()
        {
            val rate = "https://firebasestorage.googleapis.com/v0/b/powerfull-6c0bc.appspot.com/o/cuatroEstrellas.png?alt=media&token=e5900b3e-2f87-4b49-9235-1aebef684e33"
            calificacion(rate)
        }

        fiveStars.setOnClickListener()
        {
            val rate = "https://firebasestorage.googleapis.com/v0/b/powerfull-6c0bc.appspot.com/o/cincoEstrellas.png?alt=media&token=f2668188-7e70-4dd0-a050-d11a5af43767"
            calificacion(rate)
        }


        // Función para mostrar el nombre del usuario que hará el comentario
        mostrarUsuario()

        // Acción que hará al presionar el botón del fragmento
        dejarComentario.setOnClickListener()
        {
            // Función que agrega el comentario a la base de datos y lo muestra en el fragmento
            // comentarios

            agregarComentario()
            findNavController().navigate(R.id.action_dejarcomentarioFragment_to_comentariosFragment)
        }

        return view
    }

    fun calificacion(rate: String)
    {
        val user = firebaseAuth.currentUser
        val userdb = dbreference.child(user?.uid.toString())

        userdb.child("calificacion").setValue(rate)
    }

    fun agregarComentario()
    {
        val user = firebaseAuth.currentUser
        val userdb = dbreference.child(user?.uid.toString())

        //userdb.child("calificacion").setValue("https://firebasestorage.googleapis.com/v0/b/powerfull-6c0bc.appspot.com/o/unaEstrella.png?alt=media&token=3aa7299b-82be-4ce4-8a5b-4118e966df9b")
        userdb.child("usuario").setValue(nombreUsuario.text.toString())
        userdb.child("comentario").setValue(comentario.text.toString())
    }

    fun mostrarUsuario()
    {
        val user = firebaseAuth.currentUser
        FirebaseDatabase.getInstance().getReference("Usuario").child(user!!.uid)
            .addValueEventListener(object: ValueEventListener
            {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists())
                    {
                        nombreUsuario.setText(snapshot.child("name").getValue().toString())
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }
}