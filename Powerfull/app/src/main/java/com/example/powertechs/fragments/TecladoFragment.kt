package com.example.powertechs.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.powertechs.R
import com.example.powertechs.view.ui.adapter.CarritoAdapter
import com.example.powertechs.view.ui.adapter.ProductosAdapter

class TecladoFragment: Fragment() {

    //val list : MutableList<String> = mutableListOf()
    //var list1 : Array<String> = arrayOf()
    //var list2 : Array<Int> = arrayOf()
    lateinit var boton : Button
    val lista = CarritoAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_teclado, container, false)
        (activity as AppCompatActivity).supportActionBar?.title="Teclado"

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        boton = view.findViewById(R.id.botonAgregarTeclado)
        boton.setOnClickListener()
        {
            lista.agregarElementos("Teclado", "120000", R.drawable.tecladomecanicoblanco)
            findNavController().navigate(R.id.carritodecomprasFragment)
        }
    }
}