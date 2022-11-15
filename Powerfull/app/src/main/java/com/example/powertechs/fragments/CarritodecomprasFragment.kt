package com.example.powertechs.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.powertechs.R
import com.example.powertechs.view.ui.adapter.CarritoAdapter
import com.example.powertechs.view.ui.adapter.ProductosAdapter
import com.example.powertechs.view.ui.viewmodel.CarritoViewModel
import com.example.powertechs.view.ui.viewmodel.ProductosViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.lifecycle.Observer

class CarritodecomprasFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    lateinit var adapter: CarritoAdapter
    private val viewmodel by lazy { ViewModelProvider(this).get(CarritoViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_carritodecompras, container, false)
        recyclerView=view.findViewById(R.id.recyclerview_carrito)
        adapter = CarritoAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        observeData()

        (activity as AppCompatActivity).supportActionBar?.title="Carrito de compras"
        return view
    }

    fun observeData()
    {
        viewmodel.productosData().observe(viewLifecycleOwner, Observer
        {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<BottomNavigationView>(R.id.buttonNavigationMenu)

        button.setOnNavigationItemReselectedListener {
            when(it.itemId)
            {
                R.id.homeBar -> findNavController().navigate(R.id.action_carritodecomprasFragment_to_homeFragment)
                R.id.productosBar -> findNavController().navigate(R.id.action_carritodecomprasFragment_to_productosFragment)
                R.id.perfilBar -> findNavController().navigate(R.id.action_carritodecomprasFragment_to_editarmiperfilFragment)
                R.id.nav_carritodecompras -> findNavController().navigate(R.id.carritodecomprasFragment)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}