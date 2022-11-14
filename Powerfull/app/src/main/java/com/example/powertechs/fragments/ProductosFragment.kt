package com.example.powertechs.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.powertechs.R
import com.example.powertechs.view.ui.adapter.ProductosAdapter
import com.example.powertechs.view.ui.viewmodel.ProductosViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProductosFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var actionBar: ActionBar
    lateinit var lista: ProductosAdapter
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var adapter: ProductosAdapter
    private val viewmodel by lazy { ViewModelProvider(this).get(ProductosViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        recyclerView=view.findViewById(R.id.recyclerview)
        adapter = ProductosAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        observeData()
        adapter.setOnItemClickListener(object : ProductosAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                if(position == 0)
                {
                    findNavController().navigate(R.id.action_productosFragment_to_tecladoFragment)
                }

                else if(position == 1)
                {
                    findNavController().navigate(R.id.action_productosFragment_to_mouseFragment)
                }

                else if(position == 2)
                {
                    findNavController().navigate(R.id.action_productosFragment_to_computadorFragment)
                }

                else if(position == 3)
                {
                    findNavController().navigate(R.id.action_productosFragment_to_pantallaFragment2)
                }
                else
                {
                    findNavController().navigate(R.id.action_productosFragment_to_tecladoFragment)
                }
            }

        })

        (activity as AppCompatActivity).supportActionBar?.title="Productos"

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
                R.id.homeBar -> findNavController().navigate(R.id.action_productosFragment_to_homeFragment)
                R.id.productosBar -> findNavController().navigate(R.id.productosFragment)
                R.id.perfilBar -> findNavController().navigate(R.id.action_carritodecomprasFragment_to_editarmiperfilFragment)
                R.id.carritoBar -> findNavController().navigate(R.id.action_productosFragment_to_carritodecomprasFragment)
                R.id.cerrarsesionBar -> {
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_productosFragment_to_loginActivity)
                    true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}