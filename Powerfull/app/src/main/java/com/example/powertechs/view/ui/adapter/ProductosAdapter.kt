package com.example.powertechs.view.ui.adapter

import android.content.Context
import android.icu.text.CaseMap.Title
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.powertechs.R
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import com.example.powertechs.fragments.ProductosFragment
import com.example.powertechs.models.productos
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class ProductosAdapter(private val context: Context): RecyclerView.Adapter<ProductosAdapter.ViewHolder>() {


    private var productosLista = mutableListOf<productos>()

    fun setListData(data:MutableList<productos>)
    {
        productosLista = data
    }

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener)
    {
        mListener = clickListener
    }

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder{
        val v= LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_productos, ViewGroup, false)
        return ViewHolder(v, mListener)
    }

    inner class ViewHolder(itemView: View, val clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView)
    {
        fun binWeb(producto: productos)
        {
            itemView.findViewById<TextView>(R.id.title).text = producto.titulo
            itemView.findViewById<TextView>(R.id.precio).text = producto.precio
            Picasso.with(context).load(producto.image).into(itemView.findViewById<ImageView>(R.id.imagen))
            itemView.setOnClickListener()
            {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int)
    {
        val producto = productosLista[i]
        viewHolder.binWeb(producto)

    }

    override fun getItemCount(): Int {
        return if(productosLista.size > 0)
        {
            productosLista.size
        }
        else
        {
            0
        }
    }
}