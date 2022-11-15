package com.example.powertechs.repositorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.powertechs.models.productos
import com.google.firebase.firestore.FirebaseFirestore

class repo
{
    fun getProductosData(): LiveData<MutableList<productos>>
    {
        val mutableData = MutableLiveData<MutableList<productos>>()

        FirebaseFirestore.getInstance().collection("productos").get()
            .addOnSuccessListener {
                result -> val listData = mutableListOf<productos>()
                for(document in result)
                {
                    val titulo = document.getString("titulo")
                    val precio = document.getString("precio")
                    val image = document.getString("image")
                    val producto = productos(titulo!!, precio!!, image!!)
                    listData.add(producto)
                }
                mutableData.value = listData
            }
        return mutableData
    }
}