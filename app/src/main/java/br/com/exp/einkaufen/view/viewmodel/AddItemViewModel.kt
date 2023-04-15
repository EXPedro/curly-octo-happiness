package br.com.exp.einkaufen.view.viewmodel

import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddItemViewModel: ViewModel() {

    //lateinit var inputedText: String
    private lateinit var _newItems: String
    private lateinit var newItems: String

    init {
        Log.i(ADD_ITEM_VIEW_MODEL, "ViewModel criado!" )
    }

    fun firstStep (texto: String){
        _newItems = texto
        Log.i(ADD_ITEM_VIEW_MODEL, "Texto de entrada capturado!! = $_newItems")
    }



    override fun onCleared() {
        super.onCleared()
        Log.i(ADD_ITEM_VIEW_MODEL, "AddItemViewModel destruído!")
    }

    companion object {
        private const val ADD_ITEM_VIEW_MODEL = "AddItemViewModel"
    }
}