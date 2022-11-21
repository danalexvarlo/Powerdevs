package com.example.powertechs.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.powertechs.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class editarmiperfilFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbreference : DatabaseReference
    private lateinit var database: FirebaseDatabase

    lateinit var nombrecompleto: EditText
    lateinit var apellidoscompletos: EditText
    lateinit var correoEditar: EditText
    lateinit var telefono: EditText

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_editarmiperfil, container, false)

        val toolbar : Toolbar = view.findViewById(R.id.toolbar_miperfil)
        val drawerLayout: DrawerLayout = view.findViewById(R.id.miperfil_fragment)
        val navView : NavigationView = view.findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle( this.requireContext() as AppCompatActivity, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        toolbar.setTitle("Tu perfil")

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
                    findNavController().navigate(R.id.action_editarmiperfilFragment_to_homeFragment)
                    Toast.makeText(context, "Página princial", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_perfil -> {
                    findNavController().navigate(R.id.editarmiperfilFragment)
                    Toast.makeText(context, "Tu perfil", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_productos -> {
                    findNavController().navigate(R.id.action_editarmiperfilFragment_to_productosFragment)
                    Toast.makeText(context, "Productos", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_comentarios -> {
                    findNavController().navigate(R.id.action_editarmiperfilFragment_to_comentariosFragment)
                    Toast.makeText(context, "Comentarios de la tienda", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_carritodecompras ->{
                    findNavController().navigate(R.id.action_editarmiperfilFragment_to_carritodecomprasFragment)
                    Toast.makeText(context, "Carrito de compras", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_contactenos ->{
                    findNavController().navigate(R.id.action_editarmiperfilFragment_to_mapaFragment)
                    Toast.makeText(context, "Contáctanos", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_cerrarsesion ->{
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_editarmiperfilFragment_to_loginActivity)
                }
            }
            true
        }

        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbreference = database.reference.child("Usuario")
        val user = firebaseAuth.currentUser
        val userdb = dbreference.child(user?.uid.toString())
        mostrarUsuario()

        val btmcamara = view.findViewById<ImageView>(R.id.imageCambiarfoto)
        nombrecompleto = view.findViewById(R.id.nombreCompleto)
        apellidoscompletos = view.findViewById(R.id.apellidosCompletos)
        correoEditar = view.findViewById(R.id.correoEditarperfil)
        telefono = view.findViewById(R.id.telefonoEditarperfil)
        val buttonEditarPerfil = view.findViewById<Button>(R.id.buttonEditarPerfil)
        val buttonConfirmar = view.findViewById<Button>(R.id.buttonConfirmar)
        nombrecompleto.isEnabled = false
        apellidoscompletos.isEnabled = false
        correoEditar.isEnabled = false
        telefono.isEnabled = false

        buttonEditarPerfil.setOnClickListener()
        {
            if(nombrecompleto.isEnabled == false && apellidoscompletos.isEnabled == false &&
                correoEditar.isEnabled == false && telefono.isEnabled == false)
            {
                nombrecompleto.isEnabled = true
                apellidoscompletos.isEnabled = true
                telefono.isEnabled = true
            }

        }

        buttonConfirmar.setOnClickListener()
        {
            if(nombrecompleto.isEnabled == true && apellidoscompletos.isEnabled == true &&
                correoEditar.isEnabled == true && telefono.isEnabled == true)
            {
                nombrecompleto.isEnabled = false
                apellidoscompletos.isEnabled = false
                telefono.isEnabled = false

                Toast.makeText(activity as AppCompatActivity, "Se modificaron los datos correctamente", Toast.LENGTH_SHORT).show()
            }

            userdb.child("name").setValue(nombrecompleto.findViewById<EditText>(R.id.nombreCompleto).text.toString())
            userdb.child("last name").setValue(apellidoscompletos.findViewById<EditText>(R.id.apellidosCompletos).text.toString())
            userdb.child("contacto").setValue(telefono.findViewById<EditText>(R.id.telefonoEditarperfil).text.toString())
        }
        btmcamara.setOnClickListener()
        {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)
        }

        (activity as AppCompatActivity).supportActionBar?.title = "Editar perfil"
        return view
    }

    fun mostrarUsuario()
    {
        val user = firebaseAuth.currentUser
        FirebaseDatabase.getInstance().getReference("Usuario").child(user!!.uid)
            .addValueEventListener(object: ValueEventListener
            {
                override fun onDataChange(snapshot: DataSnapshot)
                {
                    if(snapshot.exists())
                    {
                        nombrecompleto.findViewById<EditText>(R.id.nombreCompleto)
                            .setText(snapshot.child("name").getValue().toString())

                        apellidoscompletos.findViewById<EditText>(R.id.apellidosCompletos)
                            .setText(snapshot.child("last name").getValue().toString())

                        correoEditar.findViewById<EditText>(R.id.correoEditarperfil)
                            .setText(snapshot.child("correo").getValue().toString())

                        telefono.findViewById<EditText>(R.id.telefonoEditarperfil)
                            .setText(snapshot.child("contacto").toString())
                    }

                }
                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView=view?.findViewById<ImageView>(R.id.imageCambiarfoto)
        if(requestCode==123)
        {
            var bitmap = data?.extras?.get("data") as Bitmap
            imageView?.setImageBitmap(bitmap)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<BottomNavigationView>(R.id.buttonNavigationMenu)

        button.setOnNavigationItemReselectedListener {
            when(it.itemId)
            {
                R.id.homeBar -> findNavController().navigate(R.id.action_editarmiperfilFragment_to_homeFragment)
                R.id.productosBar -> findNavController().navigate(R.id.action_editarmiperfilFragment_to_productosFragment)
                R.id.perfilBar -> findNavController().navigate(R.id.editarmiperfilFragment)
                R.id.carritoBar -> findNavController().navigate(R.id.action_editarmiperfilFragment_to_carritodecomprasFragment)
                R.id.contactanosBar -> findNavController().navigate(R.id.action_editarmiperfilFragment_to_mapaFragment)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}