package com.example.powertechs.fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.powertechs.R
import com.example.powertechs.view.ui.adapter.CarritoAdapter
import com.example.powertechs.view.ui.viewmodel.CarritoViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class CarritodecomprasFragment : Fragment()
{

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbreference : DatabaseReference
    private lateinit var database: FirebaseDatabase

    lateinit var totalIva : TextView
    lateinit var totalText : TextView
    lateinit var subTotalText: TextView
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
        subTotalText = view.findViewById(R.id.subtotalCarrito)
        totalText = view.findViewById(R.id.totalCarrito)
        totalIva = view.findViewById(R.id.totalIvaCarrito)

        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbreference = database.reference.child("Carrito")

        mostrarSubtotal()

        observeData()

        (activity as AppCompatActivity).supportActionBar?.title="Carrito de compras"
        return view
    }

    fun mostrarSubtotal()
    {
        val user = firebaseAuth.currentUser
        FirebaseDatabase.getInstance().getReference("Carrito").child(user!!.uid)
            .addValueEventListener(object: ValueEventListener
            {
                override fun onDataChange(snapshot: DataSnapshot)
                {
                    if(snapshot.exists())
                    {
                        var variable: Long = 0
                        try
                        {
                            for (locationSnapshot in snapshot.getChildren()) {
                                val total: String =
                                    locationSnapshot.child("total").getValue().toString()
                                variable = variable + total.toLong()
                            }
                            totalText.findViewById<TextView>(R.id.totalCarrito).text =
                                variable.toString()
                            subTotalText.findViewById<TextView>(R.id.subtotalCarrito).text =
                                (variable/1.19).toString()
                            totalIva.findViewById<TextView>(R.id.totalIvaCarrito).text =
                                (variable - (variable/1.19)).toString()
                        }
                        catch (e: java.lang.Exception)
                        {
                            subTotalText.findViewById<TextView>(R.id.subtotalCarrito).text =
                                (variable/1.19).toString()
                            subTotalText.findViewById<TextView>(R.id.subtotalCarrito).text =
                                (variable/1.19).toString()
                            totalIva.findViewById<TextView>(R.id.totalIvaCarrito).text =
                                (variable - (variable/1.19)).toString()
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            })
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