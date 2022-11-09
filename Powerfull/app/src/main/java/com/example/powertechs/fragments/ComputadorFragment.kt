package com.example.powertechs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.powertechs.R
import com.example.powertechs.view.ui.adapter.CarritoAdapter

class ComputadorFragment : Fragment() {

    lateinit var boton : Button
    val lista = CarritoAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_computador, container, false)
        (activity as AppCompatActivity).supportActionBar?.title="Productos"
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        boton = view.findViewById(R.id.botonAgregarPc)
        boton.setOnClickListener() {

            findNavController().navigate(R.id.carritodecomprasFragment)
            lista.agregarElementos("Computador", "3550000", R.drawable.computador)
        }
    }
}