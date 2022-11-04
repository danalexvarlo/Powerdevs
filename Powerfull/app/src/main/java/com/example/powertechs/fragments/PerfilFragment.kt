package com.example.powertechs.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.example.powertechs.R
import com.example.powertechs.databinding.ActivityMainBinding
import com.example.powertechs.view.ui.activities.HomeActivity
import com.example.powertechs.view.ui.activities.LoginActivity
import com.example.powertechs.view.ui.activities.ProductosActivity
import com.google.android.material.navigation.NavigationView

class PerfilFragment : Fragment()
{
    lateinit var toggle: ActionBarDrawerToggle
    companion object{
        fun newInstance() = PerfilFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_miperfil, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item!!.itemId
        if(id == R.id.nav_home)
        {
            Toast.makeText(activity, "Home", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        inflater!!.inflate(R.menu.nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}