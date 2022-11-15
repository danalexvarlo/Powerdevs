package com.example.powertechs.view.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.powertechs.models.carritoModel
import com.example.powertechs.models.productos
import com.example.powertechs.repositorio.repo
import com.example.powertechs.repositorio.repoCarrito

class CarritoViewModel: ViewModel()
{
    val repo = repoCarrito()
    fun productosData(): LiveData<MutableList<carritoModel>>
    {
        val mutableData = MutableLiveData<MutableList<carritoModel>>()
        repo.getProductosData().observeForever()
        {
                result -> mutableData.value = result
        }
        return mutableData
    }
}