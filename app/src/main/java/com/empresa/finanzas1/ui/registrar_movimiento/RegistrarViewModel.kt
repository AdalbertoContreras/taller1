package com.empresa.finanzas1.ui.registrar_movimiento

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Registrar movimiento"
    }
    val text: LiveData<String> = _text
}