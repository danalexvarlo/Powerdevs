package com.example.powertechs.view.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.powertechs.R

class CarritoAdapter: RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    val titles : MutableList<String> = mutableListOf("Computador")
    var precioProductos = mutableListOf<String>("$3.550.000")
    var image  = mutableListOf<Int>(R.drawable.computador)

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder{
        val v= LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_carrito, ViewGroup, false)
        return ViewHolder(v)
    }
    fun agregarElementos(titulo : String, precios : String, imagen : Int)
    {
        titles.add(titulo)
        precioProductos.add(precios)
        image.add(imagen)
        Log.i(titles.size.toString(), "Número de elementos de la lista")
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrecio: TextView

        init{
            itemImage = itemView.findViewById(R.id.imagenCarrito)
            itemTitle = itemView.findViewById(R.id.title)
            itemPrecio = itemView.findViewById(R.id.precio)
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        viewHolder.itemTitle.text= titles[i]
        viewHolder.itemPrecio.text= precioProductos[i]
        viewHolder.itemImage.setImageResource(image[i])
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}