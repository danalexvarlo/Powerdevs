package com.example.powertechs.view.ui.activities

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.powertechs.R
import com.example.powertechs.fragments.PerfilFragment
import com.google.android.material.navigation.NavigationView

class ProductosActivity : AppCompatActivity()
{
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerHome)

        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener{

            when(it.itemId)
            {
                R.id.nav_home -> startActivity(Intent(this, HomeActivity::class.java))
                R.id.nav_editarperfil -> Toast.makeText(applicationContext, "Editar perfil", Toast.LENGTH_SHORT).show()
                R.id.nav_productos -> startActivity(Intent(this, ProductosActivity::class.java))
                R.id.nav_carritodecompras -> Toast.makeText(applicationContext, "Carrito de Compras", Toast.LENGTH_SHORT).show()
                R.id.nav_comentarios -> Toast.makeText(applicationContext, "Comentarios", Toast.LENGTH_SHORT).show()
                R.id.nav_contactenos -> Toast.makeText(applicationContext, "Contáctenos", Toast.LENGTH_SHORT).show()
                R.id.nav_cerrarsesion -> startActivity(Intent(this, LoginActivity::class.java))
            }

            true

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if(toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}