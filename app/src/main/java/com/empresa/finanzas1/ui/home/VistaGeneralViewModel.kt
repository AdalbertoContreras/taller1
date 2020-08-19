package com.empresa.finanzas1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VistaGeneralViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Vista general"
    }
    val text: LiveData<String> = _text
}