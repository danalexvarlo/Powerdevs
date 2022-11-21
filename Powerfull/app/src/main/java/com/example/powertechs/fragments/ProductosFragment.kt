package com.example.powertechs.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
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
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProductosFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var adapter: ProductosAdapter

    lateinit var toggle : ActionBarDrawerToggle

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

        val toolbar : Toolbar = view.findViewById(R.id.toolbar_productos)
        val drawerLayout: DrawerLayout = view.findViewById(R.id.productos_fragment)
        val navView : NavigationView = view.findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle( this.requireContext() as AppCompatActivity, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        toolbar.setTitle("Productos")

        toolbar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(Gravity.START)
            }
        }

        recyclerView=view.findViewById(R.id.recyclerview)
        adapter = ProductosAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        observeData()

        navView.setNavigationItemSelectedListener()
        {
            when(it.itemId)
            {
                R.id.nav_home -> {
                    findNavController().navigate(R.id.action_productosFragment_to_homeFragment)
                    Toast.makeText(context, "Página princial", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_perfil -> {
                    findNavController().navigate(R.id.action_productosFragment_to_editarmiperfilFragment)
                    Toast.makeText(context, "Perfil", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_productos -> {
                    findNavController().navigate(R.id.productosFragment)
                    Toast.makeText(context, "Productos", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_carritodecompras ->{
                    findNavController().navigate(R.id.action_productosFragment_to_carritodecomprasFragment)
                    Toast.makeText(context, "Carrito de compras", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_comentarios ->{
                    findNavController().navigate(R.id.action_productosFragment_to_comentariosFragment)
                    Toast.makeText(context, "Comentarios de nuestra tienda", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_contactenos ->{
                    findNavController().navigate(R.id.action_productosFragment_to_contactanosFragment)
                    Toast.makeText(context, "Contáctanos", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_cerrarsesion ->{
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_productosFragment_to_loginActivity)
                }

            }
            true
        }

        adapter.setOnItemClickListener(object : ProductosAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                if(position == 0)
                {
                    findNavController().navigate(R.id.action_productosFragment_to_impresoraFragment)
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
                R.id.perfilBar -> findNavController().navigate(R.id.action_productosFragment_to_editarmiperfilFragment)
                R.id.carritoBar -> findNavController().navigate(R.id.action_productosFragment_to_carritodecomprasFragment)
                R.id.contactanosBar -> findNavController().navigate(R.id.action_productosFragment_to_contactanosFragment)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}