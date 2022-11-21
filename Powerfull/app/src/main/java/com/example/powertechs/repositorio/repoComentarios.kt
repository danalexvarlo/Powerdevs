package com.example.powertechs.repositorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.powertechs.models.carritoModel
import com.example.powertechs.models.comentariosModel
import com.example.powertechs.models.productos
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class repoComentarios
{
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbreference : DatabaseReference
    private lateinit var database: FirebaseDatabase

    fun getComentariosData(): LiveData<MutableList<comentariosModel>>
    {
        val mutableData = MutableLiveData<MutableList<comentariosModel>>()

        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbreference = database.reference.child("Comentarios")

        FirebaseDatabase.getInstance().getReference("Comentarios")
            .addValueEventListener(object: ValueEventListener
            {
                val listData = mutableListOf<comentariosModel>()
                override fun onDataChange(snapshot: DataSnapshot)
                {
                    if(snapshot.exists())
                    {
                        for (locationSnapshot in snapshot.getChildren())
                        {
                            val usuario: String =
                                locationSnapshot.child("usuario").getValue().toString()
                            val comentario: String =
                                locationSnapshot.child("comentario").getValue().toString()
                            val calificacion: String =
                                locationSnapshot.child("calificacion").getValue().toString()
                            val comentarios = comentariosModel(usuario, comentario, calificacion)
                            listData.add(comentarios)
                        }
                    }
                    mutableData.value = listData
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        return mutableData
    }
}