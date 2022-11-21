package com.example.powertechs.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.example.powertechs.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.type.LatLng

class MapaFragment : Fragment(), OnMapReadyCallback {
    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleMap: GoogleMap

    lateinit var verComentarios : Button

    lateinit var toggle: ActionBarDrawerToggle

    companion object{
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mapa, container, false)

        val toolbar : Toolbar = view.findViewById(R.id.toolbar_contactanos)
        val drawerLayout: DrawerLayout = view.findViewById(R.id.contactanos_fragment)
        val navView : NavigationView = view.findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle( this.requireContext() as AppCompatActivity, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        toolbar.setTitle("Contacto")

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
                    findNavController().navigate(R.id.action_mapaFragment_to_homeFragment)
                    Toast.makeText(context, "Página princial", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_perfil -> {
                    findNavController().navigate(R.id.action_mapaFragment_to_editarmiperfilFragment)
                    Toast.makeText(context, "Tu perfil", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_productos -> {
                    findNavController().navigate(R.id.action_mapaFragment_to_productosFragment)
                    Toast.makeText(context, "Productos", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_comentarios -> {
                    findNavController().navigate(R.id.action_mapaFragment_to_comentariosFragment)
                    Toast.makeText(context, "Comentarios de la tienda", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_carritodecompras ->{
                    findNavController().navigate(R.id.action_mapaFragment_to_carritodecomprasFragment)
                    Toast.makeText(context, "Carrito de compras", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_contactenos ->{
                    findNavController().navigate(R.id.mapaFragment)
                    Toast.makeText(context, "Contáctanos", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_cerrarsesion ->{
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_mapaFragment_to_loginActivity)
                }
            }
            true
        }

        verComentarios = view.findViewById(R.id.botonCalificar)
        verComentarios.setOnClickListener()
        {
            findNavController().navigate(R.id.action_mapaFragment_to_dejarcomentarioFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<BottomNavigationView>(R.id.buttonNavigationMenu)
        val mapFragment = this.childFragmentManager.findFragmentById(R.id.map_view)
                as SupportMapFragment
        mapFragment.getMapAsync(this)

        button.setOnNavigationItemReselectedListener {
            when(it.itemId)
            {
                R.id.homeBar -> findNavController().navigate(R.id.action_mapaFragment_to_homeFragment)
                R.id.productosBar -> findNavController().navigate(R.id.action_mapaFragment_to_productosFragment)
                R.id.perfilBar -> findNavController().navigate(R.id.action_mapaFragment_to_editarmiperfilFragment)
                R.id.carritoBar -> findNavController().navigate(R.id.action_mapaFragment_to_carritodecomprasFragment)
                R.id.contactanosBar -> findNavController().navigate(R.id.mapaFragment)
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        val colombia = com.google.android.gms.maps.model.LatLng(4.4467, -75.2393)
        map?.let {
            this.googleMap = it
            map.addMarker(MarkerOptions().position(colombia))
            }
            enableLocation()
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            com.example.powertechs.fragments.MapaFragment.Companion.REQUEST_CODE_LOCATION
                    -> if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        googleMap.isMyLocationEnabled = true
            }
            else{
                Toast.makeText(this.context,
                "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
            }
            else ->{}
        }
    }

    private fun isLocationPermissionGrated()=ContextCompat.checkSelfPermission(
        this.requireContext(),
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )==PackageManager.PERMISSION_GRANTED

    private fun enableLocation()
    {
        if(!::googleMap.isInitialized)return
        if(isLocationPermissionGrated()){
            googleMap.isMyLocationEnabled=true
        }
        else{
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission()
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale
                (this.requireActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this.context,
            "Requiere activar permisos en ajustes", Toast.LENGTH_SHORT).show()
        }
        else{
            ActivityCompat.requestPermissions(this.requireActivity(),
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                com.example.powertechs.fragments.MapaFragment.Companion.REQUEST_CODE_LOCATION
            )
        }
    }

}