package com.example.powertechs.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.powertechs.R
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity()
{
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerHome)

        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener{

            when(it.itemId)
            {
                R.id.nav_home -> Toast.makeText(applicationContext, "Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.nav_editarperfil -> Toast.makeText(applicationContext, "Editar perfil", Toast.LENGTH_SHORT).show()
                R.id.nav_productos -> startActivity(Intent(this, HomeActivity::class.java))
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