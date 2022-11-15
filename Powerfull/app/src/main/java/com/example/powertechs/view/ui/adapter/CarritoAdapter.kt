package com.example.powertechs.view.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.powertechs.R
import com.example.powertechs.models.carritoModel
import com.squareup.picasso.Picasso

class CarritoAdapter(private val context: Context): RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    private var productosCarrito = mutableListOf<carritoModel>()

    fun setListData(data:MutableList<carritoModel>)
    {
        productosCarrito = data
    }

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder{
        val v= LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_carrito, ViewGroup, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        fun binWeb(carrito: carritoModel)
        {
            itemView.findViewById<TextView>(R.id.titleCarrito).text = carrito.titulo
            itemView.findViewById<TextView>(R.id.precioCarrito).text = carrito.precio.toString()
            Picasso.with(context).load(carrito.image).into(itemView.findViewById<ImageView>(R.id.imagenCarrito))
        }

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val carrito = productosCarrito[i]
        viewHolder.binWeb(carrito)
    }

    override fun getItemCount(): Int {
        return if(productosCarrito.size > 0)
        {
            productosCarrito.size
        }
        else
        {
            0
        }
    }
}