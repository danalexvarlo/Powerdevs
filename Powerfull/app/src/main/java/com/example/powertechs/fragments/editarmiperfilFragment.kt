package com.example.powertechs.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.powertechs.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class editarmiperfilFragment : Fragment() {

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
        val btmcamara = view.findViewById<ImageView>(R.id.imageCambiarfoto)
        val nombrecompleto = view.findViewById<EditText>(R.id.nombreCompleto)
        val apellidoscompletos = view.findViewById<EditText>(R.id.apellidosCompletos)
        val correoEditar = view.findViewById<EditText>(R.id.correoEditarperfil)
        val telefono = view.findViewById<EditText>(R.id.telefonoEditarperfil)
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
                correoEditar.isEnabled = true
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
                correoEditar.isEnabled = false
                telefono.isEnabled = false
                Toast.makeText(activity as AppCompatActivity, "Se modificaron los datos correctamente", Toast.LENGTH_SHORT).show()
            }
        }
        btmcamara.setOnClickListener()
        {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)
        }

        (activity as AppCompatActivity).supportActionBar?.title = "Editar perfil"
        return view
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
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}