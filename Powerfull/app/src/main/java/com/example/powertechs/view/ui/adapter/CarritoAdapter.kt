package com.example.powertechs.view.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.powertechs.R

class CarritoAdapter: RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder{
        val v= LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_carrito, ViewGroup, false)
        return ViewHolder(v)
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

    val titles = arrayOf("Teclado", "Mouse", "Pantalla", "Impresora")
    val precio = arrayOf("$120.000", "$50.000", "$550.000", "$750.000")
    val image = arrayOf(R.drawable.tecladomecanicoblanco, R.drawable.mouse, R.drawable.pantalla, R.drawable.impresora)

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text=titles[i]
        viewHolder.itemPrecio.text=precio[i]
        viewHolder.itemImage.setImageResource(image[i])
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}