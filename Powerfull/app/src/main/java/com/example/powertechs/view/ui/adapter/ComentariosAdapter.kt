package com.example.powertechs.view.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.powertechs.R
import com.example.powertechs.models.carritoModel
import com.example.powertechs.models.comentariosModel
import com.squareup.picasso.Picasso

class ComentariosAdapter(private val context: Context): RecyclerView.Adapter<ComentariosAdapter.ViewHolder>()
{
    private var comentarios = mutableListOf<comentariosModel>()

    fun setListData(data:MutableList<comentariosModel>)
    {
        comentarios = data
    }

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder{
        val v= LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_comentarios, ViewGroup, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        fun binWeb(comentarios: comentariosModel)
        {
            itemView.findViewById<TextView>(R.id.nombreUsuario).text = comentarios.usuario
            itemView.findViewById<TextView>(R.id.comentarioHecho).text = comentarios.comentario
            Picasso.with(context).load(comentarios.calificacion)
                .into(itemView.findViewById<ImageView>(R.id.calificacionComentarios))
        }

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val coment = comentarios[i]
        viewHolder.binWeb(coment)
    }

    override fun getItemCount(): Int {
        return if(comentarios.size > 0)
        {
            comentarios.size
        }
        else
        {
            0
        }
    }
}