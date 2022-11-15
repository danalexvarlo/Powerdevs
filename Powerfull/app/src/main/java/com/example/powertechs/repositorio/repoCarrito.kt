package com.example.powertechs.repositorio

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.powertechs.models.carritoModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase

class repoCarrito
{
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbreference : DatabaseReference
    private lateinit var database: FirebaseDatabase

    fun getProductosData(): LiveData<MutableList<carritoModel>>
    {
        val mutableData = MutableLiveData<MutableList<carritoModel>>()
        firebaseAuth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        dbreference = database.reference.child("Carrito")

        val user = firebaseAuth.currentUser
        val userdb = dbreference.child(user?.uid.toString()).toString()

        FirebaseDatabase.getInstance().getReference("Carrito").child(user!!.uid)//.child("nXVMa6EOJGZmpiLNt92VFJjf65e2")
            .addValueEventListener(object: ValueEventListener
            {
                val listData = mutableListOf<carritoModel>()
                override fun onDataChange(snapshot: DataSnapshot)
                {
                    if(snapshot.exists())
                    {
                        for (locationSnapshot in snapshot.getChildren())
                        {
                            val titulo: String =
                                locationSnapshot.child("titulo").getValue().toString()
                            val precio: String =
                                locationSnapshot.child("precio").getValue().toString()
                            val image: String =
                                locationSnapshot.child("image").getValue().toString()
                            val productos = carritoModel(titulo!!, precio!!, image!!)
                            listData.add(productos)
                        }
                    }
                    mutableData.value = listData
                }
                override fun onCancelled(error: DatabaseError) {

                }
            })
        return mutableData
    }
}
