package com.example.powertechs.view.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.powertechs.models.comentariosModel
import com.example.powertechs.repositorio.repoComentarios

class ComentariosViewModel: ViewModel()
{
    val repo = repoComentarios()
    fun comentariosData(): LiveData<MutableList<comentariosModel>>
    {
        val mutableData = MutableLiveData<MutableList<comentariosModel>>()
        repo.getComentariosData().observeForever()
        {
                result -> mutableData.value = result
        }
        return mutableData
    }
}