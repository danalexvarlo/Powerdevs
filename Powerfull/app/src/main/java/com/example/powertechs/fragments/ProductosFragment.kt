package com.example.powertechs.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.powertechs.R
import com.example.powertechs.view.ui.adapter.ProductosAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductosFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var actionBar: ActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_productos, container, false)
        recyclerView=view.findViewById(R.id.recyclerview)
        val adapter = ProductosAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        (activity as AppCompatActivity).supportActionBar?.title="Productos"
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<BottomNavigationView>(R.id.buttonNavigationMenu)

        button.setOnNavigationItemReselectedListener {
            when(it.itemId)
            {
                R.id.homeBar -> findNavController().navigate(R.id.action_productosFragment_to_homeFragment)
                R.id.productosBar -> findNavController().navigate(R.id.productosFragment)
                R.id.perfilBar -> findNavController().navigate(R.id.action_productosFragment_to_miperfilFragment)
                R.id.carritoBar -> findNavController().navigate(R.id.action_productosFragment_to_carritodecomprasFragment)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}