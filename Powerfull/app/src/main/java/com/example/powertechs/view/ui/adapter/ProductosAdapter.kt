package com.example.powertechs.view.ui.adapter

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
import org.w3c.dom.Text

class ProductosAdapter: RecyclerView.Adapter<ProductosAdapter.ViewHolder>() {

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

    inner class ViewHolder(itemView: View, clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrecio: TextView

        init{
            itemImage = itemView.findViewById(R.id.imagen)
            itemTitle = itemView.findViewById(R.id.title)
            itemPrecio = itemView.findViewById(R.id.precio)
            itemView.setOnClickListener(){
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

    val titles = arrayOf("Teclado", "Mouse", "Pantalla", "Impresora", "Computador")
    val precio = arrayOf("$120.000", "$50.000", "$550.000", "$750.000", "$3.550.000")
    var image = arrayOf(R.drawable.tecladomecanicoblanco, R.drawable.mouse, R.drawable.pantalla, R.drawable.impresora, R.drawable.computador)

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text=titles[i]
        viewHolder.itemPrecio.text=precio[i]
        viewHolder.itemImage.setImageResource(image[i])

    }

    override fun getItemCount(): Int {
        return titles.size
    }
}