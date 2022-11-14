package com.example.powertechs.view.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.powertechs.models.productos
import com.example.powertechs.repositorio.repo

class ProductosViewModel: ViewModel()
{
    val repo = repo()
    fun productosData(): LiveData<MutableList<productos>>
    {
        val mutableData = MutableLiveData<MutableList<productos>>()
        repo.getProductosData().observeForever()
        {
            result -> mutableData.value = result
        }

        return mutableData
    }
}